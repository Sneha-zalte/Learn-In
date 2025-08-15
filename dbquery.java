package com.example.mocktest_app;

import android.annotation.SuppressLint;
import android.util.ArrayMap;

import androidx.annotation.NonNull;

import com.example.mocktest_app.Models.ProfileModel;
import com.example.mocktest_app.Models.Questionmodel;
import com.example.mocktest_app.Models.Rankmodel;
import com.example.mocktest_app.Models.Testmodel;
import com.example.mocktest_app.Models.categorymodel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class dbquery
{
    @SuppressLint("StaticFieldLeak")
    public static FirebaseFirestore gFirestore;
    public static List<categorymodel> gcatList = new ArrayList<>();
    public static List<Rankmodel>gUserList = new ArrayList<>();
    public static int gusersCount = 0;
    public static boolean isMeOnToplist = false;
    public static int gselectedcat_id = 0;
    public static List<String> gBookmarklist = new ArrayList<>();
    public static List<Questionmodel>gBookmrkqueList = new ArrayList<>();
    public static List<Testmodel>gtestList = new ArrayList<>();
    public static List<Questionmodel>gQuestionList = new ArrayList<>();
    public static int gselectedtest_id=0;
    public static final int NOT_VISITED =0;
    public static final int ANSWERED =1;
    public static final int UNANSWERED =2;
    public static final int REVIEW =3;
    public static int temp = 0;
    public static ProfileModel myprofile = new ProfileModel("NA",null,null,0);
    public static Rankmodel myperformance = new Rankmodel("NULL",0,1);
    public static void createuserdata(String email,
                                      String name,
                                      MyCompleteListener completeListener)
    {
        Map<String, Object> userData = new HashMap<>();
        userData.put("EMAIL_ID",email);
        userData.put("NAME",name);
        userData.put("TOTAL_SCORE",0);
        userData.put("BOOKMARKS",0);

        DocumentReference userDoc = gFirestore.collection("users").document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        gFirestore.collection("users").add(userData);

        WriteBatch batch = gFirestore.batch();
        batch.set(userDoc,userData);
        DocumentReference countDoc = gFirestore.collection("users").document("TOTAL_USERS");
        batch.update(countDoc,"COUNT", FieldValue.increment(1));
        batch.commit()
                .addOnSuccessListener(aVoid -> completeListener.onSuccess())

                .addOnFailureListener(e -> completeListener.onFailure());
    }

    public static void saveProfileData(String name, String phone, MyCompleteListener completeListener)
    {
        Map<String, Object> profileData = new ArrayMap<>();
        profileData.put("NAME",name);
        if (phone != null)
        {
            profileData.put("PHONE",phone);
        }

        gFirestore.collection("users").document(FirebaseAuth.getInstance().getUid())
                .update(profileData)
                .addOnSuccessListener(unused -> {
                    myprofile.setName(name);
                    if (phone != null)
                    {
                        myprofile.setPhone(phone);
                    }
                    completeListener.onSuccess();

                })
                .addOnFailureListener(e -> completeListener.onFailure());
    }

    public static void getUserdata(MyCompleteListener completeListener)
    {
        gFirestore.collection("users").document(FirebaseAuth.getInstance().getUid()).get()
                .addOnSuccessListener(documentSnapshot -> {
                    myprofile.setName(documentSnapshot.getString("NAME"));
                    myprofile.setEmail(documentSnapshot.getString("EMAIL_ID"));

                    if (documentSnapshot.getString("PHONE") != null)
                    {
                        myprofile.setPhone(documentSnapshot.getString("PHONE"));
                    }
                    if (documentSnapshot.get("BOOKMARKS") != null)
                    {
                        myprofile.setBookmarkCount(documentSnapshot.getLong("BOOKMARKS").intValue());
                    }
                    myperformance.setScore(documentSnapshot.getLong("TOTAL_SCORE").intValue());
                    myperformance.setName(documentSnapshot.getString("NAME"));
                    completeListener.onSuccess();
                })
                .addOnFailureListener(e -> completeListener.onFailure());
    }

    public static void loadMyScores(final MyCompleteListener completeListener)
    {
        gFirestore.collection("users").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("MY_SCORES")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        for (int i=0; i<gtestList.size(); i++)
                        {
                            int top = 0;
                            if (documentSnapshot.get(gtestList.get(i).getTestID()) != null)
                            {
                                top = documentSnapshot.getLong(gtestList.get(i).getTestID()).intValue();
                            }

                            gtestList.get(i).setTopScore(top);
                        }
                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.onFailure();
                    }
                });

    }

    public static void loadBookmrkId(MyCompleteListener completeListener)
    {
        gBookmarklist.clear();
        gFirestore.collection("users").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("BOOKMARKS")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        int count = myprofile.getBookmarkCount();
                        for (int i=0; i < count; i++)
                        {
                            String bmID = documentSnapshot.getString("BM" + String.valueOf(i+1) + "_ID");
                            gBookmarklist.add(bmID);
                        }
                        completeListener.onSuccess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.onFailure();

                    }
                });
    }

    public static void Bookmarks(MyCompleteListener completeListener)
    {
        gBookmrkqueList.clear();

        if (gBookmarklist.size() == 0)
        {
            completeListener.onSuccess();
        }

        for (int i=0; i < gBookmarklist.size(); i++)
        {
            String docID = gBookmarklist.get(i);
            gFirestore.collection("QUESTIONS").document(docID)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists())
                            {
                                gBookmrkqueList.add(new Questionmodel(
                                        documentSnapshot.getId(),
                                        documentSnapshot.getString("QUESTION"),
                                        documentSnapshot.getString("A"),
                                        documentSnapshot.getString("B"),
                                        documentSnapshot.getString("C"),
                                        documentSnapshot.getString("D"),
                                        documentSnapshot.getLong("ANSWER").intValue(),
                                        0,
                                        -1,
                                        false


                                ));
                            }
                            temp++;

                            if (temp == gBookmarklist.size())
                            {
                                completeListener.onSuccess();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            completeListener.onFailure();

                        }
                    });



        }
    }

    public static void getTopUsers(MyCompleteListener completeListener)
    {
        gUserList.clear();

        String MyUID = FirebaseAuth.getInstance().getUid();
        gFirestore.collection("users")
                .whereGreaterThan("TOTAL_SCORE",0)
                .orderBy("TOTAL_SCORE", Query.Direction.DESCENDING)
                .limit(20)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    int rank = 1;

                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots)
                    {
                        gUserList.add(new Rankmodel(
                                doc.getString("NAME"),
                                doc.getLong("TOTAL_SCORE").intValue(),
                                rank
                        ));

                        if (MyUID.compareTo(doc.getId()) == 0)
                        {
                            isMeOnToplist = true;
                            myperformance.setRank(rank);
                        }
                        rank++;
                    }
                    completeListener.onSuccess();
                })
                .addOnFailureListener(e -> completeListener.onFailure());

    }

    public static void getUserCount(MyCompleteListener completeListener)
    {
        gFirestore.collection("users").document("TOTAL_USERS")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    gusersCount = documentSnapshot.getLong("COUNT").intValue();

                    completeListener.onSuccess();

                })
                .addOnFailureListener(e -> completeListener.onFailure());

    }

    public static void saveResult(int score, MyCompleteListener completeListener)
    {
        WriteBatch batch = gFirestore.batch();

        //Bookmark
        Map<String,Object>bmData = new ArrayMap<>();
        for (int i=0 ; i< gBookmarklist.size(); i++)
        {
            bmData.put("BM" + String.valueOf(i+1) + "_ID" , gBookmarklist.get(i));
        }
        DocumentReference bmDoc = gFirestore.collection("users").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("BOOKMARKS");

        batch.set(bmDoc,bmData);

        DocumentReference userDoc = gFirestore.collection("users").document(FirebaseAuth.getInstance().getUid());

        Map<String,Object> userData = new ArrayMap<>();
        userData.put("TOTAL_SCORE",score);
        userData.put("BOOKMARKS",gBookmarklist.size());

        batch.update(userDoc,userData);

        if (score > gtestList.get(gselectedtest_id).getTopScore())
        {
            DocumentReference scoreDoc = userDoc.collection("USER_DATA").document("MY_SCORES");
            Map<String,Object>testData = new ArrayMap<>();
            testData.put(gtestList.get(gselectedtest_id).getTestID(),score);
            batch.set(scoreDoc,testData, SetOptions.merge());

        }

        batch.commit()
                .addOnSuccessListener(unused -> {

                    if(score > gtestList.get(gselectedtest_id).getTopScore())
                    {
                        gtestList.get(gselectedtest_id).setTopScore(score);
                    }

                    myperformance.setScore(score);
                    completeListener.onSuccess();
                })
                .addOnFailureListener(e -> completeListener.onFailure());
    }

    public static void loadCategories(MyCompleteListener completeListener)
    {
        gcatList.clear();
        gFirestore.collection("LEARN-IN").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots)
                    {
                        docList.put(doc.getId(), doc);
                    }
                    QueryDocumentSnapshot catListdoc = docList.get("CATEGORIES");

                    long catCount = catListdoc.getLong("COUNT");

                    for (int i=1; i<= catCount; i++)
                    {
                        String catID = catListdoc.getString("CAT" + i +"_ID");

                        QueryDocumentSnapshot catdoc = docList.get(catID);
                        int nooftest = catdoc.getLong("NO_OF_TESTS").intValue();

                        String catName = catdoc.getString("CAT_NAME");

                        gcatList.add(new categorymodel(catID,catName,nooftest));
                    }
                    completeListener.onSuccess();
                })
                .addOnFailureListener(e -> completeListener.onFailure());

    }

    public static void loadQuestions(MyCompleteListener completeListener)
    {
        gQuestionList.clear();
        gFirestore.collection("QUESTIONS")
                .whereEqualTo("CATEGORY",gcatList.get(gselectedcat_id).getDocID())
                .whereEqualTo("TEST",gtestList.get(gselectedtest_id).getTestID())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots)
                    {
                        boolean isBookmarked = false;

                        if (gBookmarklist.contains(doc.getId()))
                            isBookmarked=true;


                        gQuestionList.add(new Questionmodel(
                                doc.getId(),
                                doc.getString("QUESTION"),
                                doc.getString("A"),
                                doc.getString("B"),
                                doc.getString("C"),
                                doc.getString("D"),
                                doc.getLong("ANSWER").intValue(),
                                -1,
                                NOT_VISITED,
                                isBookmarked
                        ));
                    }
                    completeListener.onSuccess();

                })
                .addOnFailureListener(e -> completeListener.onFailure());
    }

    public static void loadTestdata(MyCompleteListener completeListener)
    {
        gtestList.clear();
        gFirestore.collection("LEARN-IN").document(gcatList.get(gselectedcat_id).getDocID())
                .collection("TESTS_LIST").document("TESTS_INFO")
                .get()
                .addOnSuccessListener(documentSnapshot -> {

                    int noofTest = gcatList.get(gselectedcat_id).getNoOfTests();
                    for (int i = 1; i <= noofTest; i++)
                    {
                        gtestList.add(new Testmodel(
                                documentSnapshot.getString("TEST" + String.valueOf(i) + "_ID"),
                                0,
                                documentSnapshot.getLong("TEST" + String.valueOf(i) + "_TIME").intValue()
                        ));
                    }
                    completeListener.onSuccess();
                })
                .addOnFailureListener(e -> completeListener.onFailure());

    }

    public static void loadData(MyCompleteListener completeListener)
    {
        loadCategories(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                getUserdata(new MyCompleteListener() {
                    @Override
                    public void onSuccess() {
                        getUserCount(new MyCompleteListener() {
                            @Override
                            public void onSuccess() {
                                loadBookmrkId(completeListener);
                                completeListener.onSuccess();

                            }

                            @Override
                            public void onFailure() {
                                completeListener.onFailure();
                            }
                        });
                    }

                    @Override
                    public void onFailure() {
                        completeListener.onFailure();
                    }
                });
            }
            @Override
            public void onFailure() {
                completeListener.onFailure();

            }
        });
    }




}
