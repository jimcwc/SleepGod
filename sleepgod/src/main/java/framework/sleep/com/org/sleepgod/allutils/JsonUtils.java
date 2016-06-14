package framework.sleep.com.org.sleepgod.allutils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import framework.sleep.com.org.sleepgod.request.RFEntityImp;

/**
 * Created  on 2014/10/5.
 */
public class JsonUtils {
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    private JSONObject mJsonObj;

    public JsonUtils() {
        this(null);
    }

    public JsonUtils(JSONObject object) {
        if (object == null) {
            mJsonObj = new JSONObject();
        } else {
            mJsonObj = object;
        }
    }

    /**
     * 获取服务器状态码
     *
     * @return
     */
    public final int getCode() {
        return getInt(KEY_CODE);
    }

    /**
     * 获取服务器对应的状态码提示信息
     *
     * @return
     */
    public final String getMsg() {
        return getString(KEY_MSG);
    }

    /**
     * 获取服务器返回的整个字符串
     *
     * @return
     */
    public final String getResponse() {
        return mJsonObj.toString();
    }


    /**
     * 获取对象list
     *
     * @param key
     * @param t
     * @return
     */
    public <T extends RFEntityImp> List<T> getEntityList(String key, T t) {
        if (!mJsonObj.has(key)) {
            return null;
        }

        try {
            JSONArray jsArr = mJsonObj.getJSONArray(key);
            if (jsArr == null || jsArr.length() == 0) {
                return null;
            }
            List<T> res = new ArrayList<T>();
            T nt = t;
            for (int i = 0; i < jsArr.length(); i++) {
                if (nt == null) {
                    nt = t.newObject();
                }
                JSONObject mObject = jsArr.getJSONObject(i);
                nt.praseFromJson(new JsonUtils(mObject));
                res.add(nt);
                nt = null;
            }
            return res;
        } catch (JSONException e) {
        }
        return null;
    }

    /**
     * 获取对象list
     *
     * @param parentKey
     * @param childKey
     * @param t
     * @return
     */
    public <T extends RFEntityImp> List<T> getEntityList(String parentKey,
                                                         String childKey, T t) {

        try {
            JSONObject parentObj = mJsonObj.getJSONObject(parentKey);
            if (parentObj == null) {
                return null;
            }

            JSONArray jsArr = parentObj.getJSONArray(childKey);
            if (jsArr == null || jsArr.length() == 0) {
                return null;
            }
            List<T> res = new ArrayList<T>();
            T nt = t;
            for (int i = 0; i < jsArr.length(); i++) {
                if (nt == null) {
                    nt = t.newObject();
                }
                nt.praseFromJson(new JsonUtils(jsArr.getJSONObject(i)));
                res.add(nt);
                nt = null;
            }
            return res;
        } catch (JSONException e) {
        }
        return null;
    }

    public <T extends RFEntityImp> T getEntity(String key, T t) {
        T nt = t;
        try {
            JSONObject object = mJsonObj.getJSONObject(key);
            nt.praseFromJson(new JsonUtils(object));
            return nt;
        } catch (JSONException e) {
        }
        return null;

    }

    public <T extends RFEntityImp> T getEntity(String parentKey, String childKey,
                                               T t) {
        try {
            JSONObject parentObject = mJsonObj.getJSONObject(parentKey);
            if (parentObject == null) {
                return null;
            }
            T nt = t;
            JSONObject childObject = parentObject.getJSONObject(childKey);
            nt.praseFromJson(new JsonUtils(childObject));
            return nt;
        } catch (JSONException e) {
        }
        return null;

    }

    public int getInt(String key) {
        if (mJsonObj == null) {
            return Integer.MIN_VALUE;
        }
        if (mJsonObj.has(key)) {
            try {
                return mJsonObj.getInt(key);
            } catch (JSONException e) {
                return Integer.MIN_VALUE;
            }
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public String getString(String key) {

        if (mJsonObj.has(key)) {
            try {
                return mJsonObj.getString(key);
            } catch (JSONException e) {
                return "";
            }
        } else {
            return "";
        }
    }

    public String getString(String parentKey, String childKey) {
        if (mJsonObj.has(parentKey)) {
            try {
                JSONObject object = mJsonObj.getJSONObject(parentKey);
                if (object.has(childKey)) {
                    return object.getString(childKey);
                } else {
                    return "";
                }
            } catch (JSONException e) {
                return "";
            }
        } else {
            return "";
        }
    }

    public double getDouble(String key) {
        if (mJsonObj.has(key)) {
            try {
                return mJsonObj.getDouble(key);
            } catch (JSONException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public long getLong(String key) {
        if (mJsonObj.has(key)) {
            try {
                return mJsonObj.getLong(key);
            } catch (JSONException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }


    public boolean getBoolean(String key) {
        if (mJsonObj.has(key)) {
            try {
                return mJsonObj.getBoolean(key);
            } catch (JSONException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public JSONObject getJSONObject(String key) {
        if (mJsonObj.has(key)) {
            try {
                return mJsonObj.getJSONObject(key);
            } catch (JSONException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public JSONArray getJSONArray(String key) {
        if (mJsonObj.has(key)) {
            try {
                return mJsonObj.getJSONArray(key);
            } catch (JSONException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<String> getStringList(String key) {
        ArrayList<String> list = new ArrayList<String>();
        if (mJsonObj.has(key)) {
            try {
                JSONArray array = mJsonObj.getJSONArray(key);
                for (int i = 0; i < array.length(); i++) {
                    String r = String.valueOf(array.get(i));
//                    String r = array.get(i).toString();
                    list.add(r);
                }
                return list;
            } catch (JSONException e) {
                return list;
            }
        } else {
            return list;
        }
    }



}
