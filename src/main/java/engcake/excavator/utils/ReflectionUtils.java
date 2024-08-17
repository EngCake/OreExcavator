package engcake.excavator.utils;

import net.bytebuddy.utility.nullability.NeverNull;

import java.lang.reflect.Field;

public class ReflectionUtils {
    @NeverNull
    public static <ResultT, ObjectT> ResultT getPrivateField(ObjectT object, Class<ObjectT> objectClass, String fieldName) {
        try {
            Field field = objectClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (ResultT) field.get(object);
        } catch (Exception e) {
            String errorMessage = String.format(
                    "Couldn't read %s field from SettingsForm, this probably means mod is outdated",
                    fieldName
            );
            throw new RuntimeException(errorMessage);
        }
    }

    @NeverNull
    public static <ObjectT, ValueT> void setPrivateField(
            ObjectT object,
            Class<ObjectT> objectClass,
            String fieldName,
            ValueT value
    ) {
        try {
            Field field = objectClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            String errorMessage = String.format(
                    "Couldn't read %s field from SettingsForm, this probably means mod is outdated",
                    fieldName
            );
            throw new RuntimeException(errorMessage);
        }
    }
}
