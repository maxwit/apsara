//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.dingdingdemo;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.util.*;

public class MapUtil {
    public MapUtil() {
    }

    public static Map<String, String> objectToMap(Object object, String... ignore) {
        Map<String, String> tempMap = new LinkedHashMap();
        Iterator var3 = getAllFields(object.getClass()).iterator();

        while (true) {
            while (true) {
                Field field;
                Object o;
                do {
                    do {
                        do {
                            do {
                                do {
                                    boolean ig;
                                    do {
                                        if (!var3.hasNext()) {
                                            return tempMap;
                                        }

                                        field = (Field) var3.next();
                                        if (!field.isAccessible()) {
                                            field.setAccessible(true);
                                        }

                                        ig = false;
                                        if (ignore != null && ignore.length > 0) {
                                            String[] var6 = ignore;
                                            int var7 = ignore.length;

                                            for (int var8 = 0; var8 < var7; ++var8) {
                                                String i = var6[var8];
                                                ig = i.equals(field.getName());
                                                if (ig) {
                                                    break;
                                                }
                                            }
                                        }
                                    } while (ig);

                                    o = null;

                                    try {
                                        o = field.get(object);
                                    } catch (Exception var10) {
                                        var10.printStackTrace();
                                    }
                                } while (field.getName().equals("serialVersionUID"));
                            } while (field.getName().equals("FAIL"));
                        } while (field.getName().equals("OKMSG"));
                    } while (field.getName().equals("SUCCESS"));
                } while (field.getName().equals("hmac"));

                XmlElement annotation = (XmlElement) field.getAnnotation(XmlElement.class);
                if (annotation == null) {
                    tempMap.put(field.getName(), o == null ? "" : o.toString());
                } else {
                    String name = annotation.name();
                    if (StringUtil.isNotEmpty(name) && !StringUtil.stringEquals(name, "##default")) {
                        tempMap.put(name, o == null ? "" : o.toString());
                    } else {
                        tempMap.put(field.getName(), o == null ? "" : o.toString());
                    }
                }
            }
        }
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if (!clazz.equals(Object.class)) {
            List<Field> fields = new ArrayList(Arrays.asList(clazz.getDeclaredFields()));
            List<Field> fields2 = getAllFields(clazz.getSuperclass());
            if (fields2 != null) {
                fields.addAll(fields2);
            }

            return fields;
        } else {
            return null;
        }
    }
}
