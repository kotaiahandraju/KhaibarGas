package com.aurospaces.neighbourhood.util;
import java.util.HashMap;
import java.util.Set;
public class KKK {

public static void main(String a[]){
HashMap<String, String> hm = new HashMap<String, String>();
hm.put("first", "FIRST INSERTED");
hm.put("second", "SECOND INSERTED");
hm.put("third","THIRD INSERTED");
//System.out.println(hm);
Set<String> keys = hm.keySet();
for(String key: keys){
System.out.println(key +' '+hm.get(key));}
}
}