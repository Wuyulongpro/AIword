package com.example.aiword.webService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 22842 on 2019/1/23.
 */

public class WordInfo {

    /**
     * attList : [{"wordmeaning":"操作，处理;巧妙地控制;操纵;[医] 推拿，调整","wordatt":"vt.","wordID":1088}]
     * word : {"examplesentence":"0","wordpart":"manipulating","wordpast_part":"manipulated","wordpast":"manipulated","wordplural":"0","wordcomparative":"0","wordsuperlative":"0","wordID":1088,"word":"manipulate","wordthird":"manipulates","wordread":"0"}
     */

    private List<AttListBean> attList;
    private WordBean word;

    public static WordInfo objectFromData(String str) {

        return new Gson().fromJson(str, WordInfo.class);
    }

    public static WordInfo objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), WordInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<WordInfo> arrayWordInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<WordInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<WordInfo> arrayWordInfoFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<WordInfo>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public WordBean getWord() {
        return word;
    }

    public void setWord(WordBean word) {
        this.word = word;
    }

    public List<AttListBean> getAttList() {
        return attList;
    }

    public void setAttList(List<AttListBean> attList) {
        this.attList = attList;
    }

    public static class WordBean {
        /**
         * examplesentence : 0
         * wordpart : manipulating
         * wordpast_part : manipulated
         * wordpast : manipulated
         * wordplural : 0
         * wordcomparative : 0
         * wordsuperlative : 0
         * wordID : 1088
         * word : manipulate
         * wordthird : manipulates
         * wordread : 0
         */

        private String examplesentence;
        private String levenshtein;
        private String wordpart;
        private String wordpast_part;
        private String wordpast;
        private String wordplural;
        private String wordcomparative;
        private String wordsuperlative;
        private int wordID;
        private String word;
        private String wordthird;
        private String wordread;

        public static WordBean objectFromData(String str) {

            return new Gson().fromJson(str, WordBean.class);
        }

        public static WordBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), WordBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<WordBean> arrayWordBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<WordBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<WordBean> arrayWordBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<WordBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getExamplesentence() {
            return examplesentence;
        }

        public void setExamplesentence(String examplesentence) {
            this.examplesentence = examplesentence;
        }
        public String getLevenshtein(){
            return levenshtein;
        }
        public void setLevenshtein(String levenshtein){
            this.levenshtein = levenshtein;
        }
        public String getWordpart() {
            return wordpart;
        }

        public void setWordpart(String wordpart) {
            this.wordpart = wordpart;
        }

        public String getWordpast_part() {
            return wordpast_part;
        }

        public void setWordpast_part(String wordpast_part) {
            this.wordpast_part = wordpast_part;
        }

        public String getWordpast() {
            return wordpast;
        }

        public void setWordpast(String wordpast) {
            this.wordpast = wordpast;
        }

        public String getWordplural() {
            return wordplural;
        }

        public void setWordplural(String wordplural) {
            this.wordplural = wordplural;
        }

        public String getWordcomparative() {
            return wordcomparative;
        }

        public void setWordcomparative(String wordcomparative) {
            this.wordcomparative = wordcomparative;
        }

        public String getWordsuperlative() {
            return wordsuperlative;
        }

        public void setWordsuperlative(String wordsuperlative) {
            this.wordsuperlative = wordsuperlative;
        }

        public int getWordID() {
            return wordID;
        }

        public void setWordID(int wordID) {
            this.wordID = wordID;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getWordthird() {
            return wordthird;
        }

        public void setWordthird(String wordthird) {
            this.wordthird = wordthird;
        }

        public String getWordread() {
            return wordread;
        }

        public void setWordread(String wordread) {
            this.wordread = wordread;
        }
    }

    public static class AttListBean {
        /**
         * wordmeaning : 操作，处理;巧妙地控制;操纵;[医] 推拿，调整
         * wordatt : vt.
         * wordID : 1088
         */

        private String wordmeaning;
        private String wordatt;
        private int wordID;

        public static AttListBean objectFromData(String str) {

            return new Gson().fromJson(str, AttListBean.class);
        }

        public static AttListBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), AttListBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<AttListBean> arrayAttListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<AttListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<AttListBean> arrayAttListBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<AttListBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getWordmeaning() {
            return wordmeaning;
        }

        public void setWordmeaning(String wordmeaning) {
            this.wordmeaning = wordmeaning;
        }

        public String getWordatt() {
            return wordatt;
        }

        public void setWordatt(String wordatt) {
            this.wordatt = wordatt;
        }

        public int getWordID() {
            return wordID;
        }

        public void setWordID(int wordID) {
            this.wordID = wordID;
        }
    }
}
