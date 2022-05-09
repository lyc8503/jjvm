package vjvm.runtime;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import vjvm.classloader.JClassLoader;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.FieldInfo;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.runtime.classdata.constant.ClassInfoConstant;
import vjvm.runtime.classdata.constant.UTF8Constant;
import vjvm.utils.UnimplementedError;

import java.io.DataInput;
import java.io.InvalidClassException;

import static vjvm.classfiledefs.ClassAccessFlags.*;

public class JClass {
    @Getter
    private final JClassLoader classLoader;
    @Getter
    private final int minorVersion;
    @Getter
    private final int majorVersion;
    @Getter
    private final ConstantPool constantPool;
    @Getter
    private final int accessFlags;

    private final int[] interfaces;
    private final FieldInfo[] fields;
    private final MethodInfo[] methods;
    private final Attribute[] attributes;

    @Getter
    private final String thisClass;

    @Getter
    private final String superClass;



    @SneakyThrows
    public JClass(DataInput dataInput, JClassLoader classLoader) {
        this.classLoader = classLoader;

        // check magic number
        var magic = dataInput.readInt();
        if (magic != 0xcafebabe) {
            throw new InvalidClassException(String.format(
                "Wrong magic number, expected: 0xcafebabe, got: 0x%x", magic));
        }

        minorVersion = dataInput.readUnsignedShort();
        majorVersion = dataInput.readUnsignedShort();

        constantPool = new ConstantPool(dataInput, this);
        accessFlags = dataInput.readUnsignedShort();

        int thisIndex = dataInput.readUnsignedShort();
        int superIndex = dataInput.readUnsignedShort();

        thisClass = ((ClassInfoConstant) (constantPool.constant(thisIndex))).name();
        superClass = ((ClassInfoConstant) (constantPool.constant(superIndex))).name();


        int interfacesCount = dataInput.readUnsignedShort();
        interfaces = new int[interfacesCount];

        for (int i = 0; i < interfacesCount; i++) {
            interfaces[i] = dataInput.readUnsignedShort();
        }

        int fieldsCount = dataInput.readUnsignedShort();
        fields = new FieldInfo[fieldsCount];
        for (int i = 0; i < fieldsCount; i++) {
            fields[i] = new FieldInfo(dataInput, this);
        }

        int methodsCount = dataInput.readUnsignedShort();
        methods = new MethodInfo[methodsCount];
        for (int i = 0; i < methodsCount; i++) {
            methods[i] = new MethodInfo(dataInput, this);
        }

        int attributesCount = dataInput.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = Attribute.constructFromData(dataInput, constantPool);
        }

//        throw new UnimplementedError(
//            "TODO: you need to construct thisClass, superClass, interfaces, fields, "
//                + "methods, and attributes from dataInput in lab 1.2; remove this for lab 1.1."
//                + "Some of them are not defined; you need to define them yourself");
    }

    public boolean public_() {
        return (accessFlags & ACC_PUBLIC) != 0;
    }

    public boolean final_() {
        return (accessFlags & ACC_FINAL) != 0;
    }

    public boolean super_() {
        return (accessFlags & ACC_SUPER) != 0;
    }

    public boolean interface_() {
        return (accessFlags & ACC_INTERFACE) != 0;
    }

    public boolean abstract_() {
        return (accessFlags & ACC_ABSTRACT) != 0;
    }

    public boolean synthetic() {
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }

    public boolean annotation() {
        return (accessFlags & ACC_ANNOTATION) != 0;
    }

    public boolean enum_() {
        return (accessFlags & ACC_ENUM) != 0;
    }

    public boolean module() {
        return (accessFlags & ACC_MODULE) != 0;
    }

    public int interfacesCount() {
        return interfaces.length;
    }

    public String interfaceName (int index) {
        return ((ClassInfoConstant) constantPool.constant(interfaces[index])).name();
    }

    public int fieldsCount() {
        return fields.length;
    }

    public FieldInfo field(int index) {
        return fields[index];
    }

    public int methodsCount() {
        return methods.length;
    }

    public MethodInfo method(int index) {
        return methods[index];
    }

}
