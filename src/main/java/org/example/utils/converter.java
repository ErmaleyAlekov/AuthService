package org.example.utils;

import org.example.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class converter {
    //Конвертер классов, принимает 2 объекта для конвертации
    //ищет одинаковые имена функций сэтеров и гетеров подставляя значения.
    public static <T> T convert(T result, Object obj){
        if (obj!=null&&result!=null){
            try{
                Class<?> resclass = result.getClass();
                Class<?> c = obj.getClass();
                for (Method method : resclass.getMethods()) {
                    if (splitBefore(method.getName(),3 ).equals("set")){
                        for (Method methodObj :c.getMethods()) {
                            if(splitBefore(methodObj.getName(), 3).equals("get")
                                    &&splitAfter(methodObj.getName(), 3).equals(splitAfter(method.getName(), 3))
                                    && methodObj.invoke(obj)!=null
                            ){
                                if (checkParamTypes(method, methodObj))
                                    method.invoke(result, methodObj.invoke(obj));
                                else {
                                    Type[] setParamT = method.getParameterTypes();
                                    Object getObj = methodObj.invoke(obj);
                                    Object setObj = Class.forName(setParamT[0].getTypeName()).newInstance();
                                    convert(setObj,getObj);
                                    method.invoke(result,setObj);
                                }
                            }
                            else if (splitBefore(methodObj.getName(), 2).equals("is") &&
                                    splitAfter(methodObj.getName(), 2).equals(splitAfter(method.getName(), 3))
                                    && methodObj.invoke(obj)!=null
                            ) {
                                method.invoke(result,methodObj.invoke(obj));
                            }
                        }
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Logger.addLog(e.getMessage());
            }
        }
        return result;
    }

    public static boolean checkParamTypes(Method setter, Method getter) {
        if (setter != null && getter != null) {
            Type gret = getter.getReturnType();
            Type[] setParamT = setter.getParameterTypes();
            return gret.getTypeName().equals(setParamT[0].getTypeName());
        }
        return false;
    }
    public static <T,S> List<T> convertList(List<S> lst, Class<T> tClass){
        List<T> result = new ArrayList<>();
        if (lst !=null && tClass!=null){
            try {
                for (S o : lst){
                    Object newObj = Class.forName(tClass.getTypeName()).newInstance();
                    convert(newObj,o);
                    result.add((T) newObj);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Logger.addLog(e.getMessage());
            }
        }
        return result;
    }
    public static String splitBefore(String str, int size){
        StringBuilder res = new StringBuilder();
        if (str!=null){
            char[] array = str.toCharArray();
            if (size > 0&&str.length()>=size){
                for (int i = 0;size>0;size--,i++) {
                    res.append(array[i]);
                }
            }
        }
        return res.toString();
    }
    public static String splitAfter(String str, int size){
        StringBuilder res = new StringBuilder();
        if (str!=null){
            char[] array = str.toCharArray();
            if (size > 0&&str.length()>size){
                for (char c:array) {
                    if (size<=0)
                        res.append(c);
                    size--;
                }
            }
        }
        return res.toString();
    }
}
