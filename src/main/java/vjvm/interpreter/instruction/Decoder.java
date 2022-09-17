package vjvm.interpreter.instruction;

import lombok.var;
import vjvm.interpreter.instruction.comparisons.IF;
import vjvm.interpreter.instruction.comparisons.IF_ACMP;
import vjvm.interpreter.instruction.comparisons.IF_ICMP;
import vjvm.interpreter.instruction.comparisons.CMP;
import vjvm.interpreter.instruction.constants.LDC;
import vjvm.interpreter.instruction.constants.NOP;
import vjvm.interpreter.instruction.constants.XCONST_Y;
import vjvm.interpreter.instruction.constants.PUSH;
import vjvm.interpreter.instruction.control.GOTO;
import vjvm.interpreter.instruction.control.RETURN;
import vjvm.interpreter.instruction.conversions.X2Y;
import vjvm.interpreter.instruction.extended.IF_NULL;
import vjvm.interpreter.instruction.loads.XALOAD;
import vjvm.interpreter.instruction.loads.XLOAD_Y;
import vjvm.interpreter.instruction.math.BIOP;
import vjvm.interpreter.instruction.math.INC;
import vjvm.interpreter.instruction.math.NEG;
import vjvm.interpreter.instruction.math.SHX;
import vjvm.interpreter.instruction.references.*;
import vjvm.interpreter.instruction.stack.DUP;
import vjvm.interpreter.instruction.stack.POP;
import vjvm.interpreter.instruction.stack.SWAP;
import vjvm.interpreter.instruction.stores.XASTORE;
import vjvm.interpreter.instruction.stores.XSTORE_Y;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.error.UnimplementedInstructionError;

import java.util.function.BiFunction;

public class Decoder {

    public static Instruction decode(ProgramCounter pc, MethodInfo method) {
        var opcode = Byte.toUnsignedInt(pc.byte_());
        if (Decoder.decodeTable[opcode] == null) {
            throw new UnimplementedInstructionError(opcode);
        }
        return Decoder.decodeTable[opcode].apply(pc, method);
    }

    @SafeVarargs
    static BiFunction<ProgramCounter, MethodInfo, Instruction>[] of(
        BiFunction<ProgramCounter, MethodInfo, Instruction>... elem) {
        return elem;
    }

    static final BiFunction<ProgramCounter, MethodInfo, Instruction>[] decodeTable = of(
        // @formatter:off
        /* 0x00 */  NOP::new, XCONST_Y::ACONST_NULL, XCONST_Y::ICONST_M1, XCONST_Y::ICONST_0,
        /* 0x04 */  XCONST_Y::ICONST_1, XCONST_Y::ICONST_2, XCONST_Y::ICONST_3, XCONST_Y::ICONST_4,
        /* 0x08 */  XCONST_Y::ICONST_5, XCONST_Y::LCONST_0, XCONST_Y::LCONST_1, XCONST_Y::FCONST_0,
        /* 0x0c */  XCONST_Y::FCONST_1, XCONST_Y::FCONST_2, XCONST_Y::DCONST_0, XCONST_Y::DCONST_1,
        /* 0x10 */  PUSH::BIPUSH, PUSH::SIPUSH, LDC::LDC, LDC::LDC_W,
        /* 0x14 */  LDC::LDC2_W, XLOAD_Y::ILOAD, XLOAD_Y::LLOAD, XLOAD_Y::FLOAD,
        /* 0x18 */  XLOAD_Y::DLOAD, XLOAD_Y::ALOAD, XLOAD_Y::ILOAD_0, XLOAD_Y::ILOAD_1,
        /* 0x1c */  XLOAD_Y::ILOAD_2, XLOAD_Y::ILOAD_3, XLOAD_Y::LLOAD_0, XLOAD_Y::LLOAD_1,
        /* 0x20 */  XLOAD_Y::LLOAD_2, XLOAD_Y::LLOAD_3, XLOAD_Y::FLOAD_0, XLOAD_Y::FLOAD_1,
        /* 0x24 */  XLOAD_Y::FLOAD_2, XLOAD_Y::FLOAD_3, XLOAD_Y::DLOAD_0, XLOAD_Y::DLOAD_1,
        /* 0x28 */  XLOAD_Y::DLOAD_2, XLOAD_Y::DLOAD_3, XLOAD_Y::ALOAD_0, XLOAD_Y::ALOAD_1,
        /* 0x2c */  XLOAD_Y::ALOAD_2, XLOAD_Y::ALOAD_3, XALOAD::IALOAD, XALOAD::LALOAD,
        /* 0x30 */  XALOAD::FALOAD, XALOAD::DALOAD, XALOAD::AALOAD, XALOAD::BALOAD,
        /* 0x34 */  XALOAD::CALOAD, XALOAD::SALOAD, XSTORE_Y::ISTORE, XSTORE_Y::LSTORE,
        /* 0x38 */  XSTORE_Y::FSTORE, XSTORE_Y::DSTORE, XSTORE_Y::ASTORE, XSTORE_Y::ISTORE_0,
        /* 0x3c */  XSTORE_Y::ISTORE_1, XSTORE_Y::ISTORE_2, XSTORE_Y::ISTORE_3, XSTORE_Y::LSTORE_0,
        /* 0x40 */  XSTORE_Y::LSTORE_1, XSTORE_Y::LSTORE_2, XSTORE_Y::LSTORE_3, XSTORE_Y::FSTORE_0,
        /* 0x44 */  XSTORE_Y::FSTORE_1, XSTORE_Y::FSTORE_2, XSTORE_Y::FSTORE_3, XSTORE_Y::DSTORE_0,
        /* 0x48 */  XSTORE_Y::DSTORE_1, XSTORE_Y::DSTORE_2, XSTORE_Y::DSTORE_3, XSTORE_Y::ASTORE_0,
        /* 0x4c */  XSTORE_Y::ASTORE_1, XSTORE_Y::ASTORE_2, XSTORE_Y::ASTORE_3, XASTORE::IASTORE,
        /* 0x50 */  XASTORE::LASTORE, XASTORE::FASTORE, XASTORE::DASTORE, XASTORE::AASTORE,
        /* 0x54 */  XASTORE::BASTORE, XASTORE::CASTORE, XASTORE::SASTORE, POP::POP,
        /* 0x58 */  POP::POP2, DUP::DUP, DUP::DUP_X1, DUP::DUP_X2,
        /* 0x5c */  DUP::DUP2, DUP::DUP2_X1, DUP::DUP2_X2, SWAP::new,
        /* 0x60 */  BIOP::IADD, BIOP::LADD, BIOP::FADD, BIOP::DADD,
        /* 0x64 */  BIOP::ISUB, BIOP::LSUB, BIOP::FSUB, BIOP::DSUB,
        /* 0x68 */  BIOP::IMUL, BIOP::LMUL, BIOP::FMUL, BIOP::DMUL,
        /* 0x6c */  BIOP::IDIV, BIOP::LDIV, BIOP::FDIV, BIOP::DDIV,
        /* 0x70 */  BIOP::IREM, BIOP::LREM, BIOP::FREM, BIOP::DREM,
        /* 0x74 */  NEG::INEG, NEG::LNEG, NEG::FNEG, NEG::DNEG,
        /* 0x78 */  SHX::ISHL, SHX::LSHL, SHX::ISHR, SHX::LSHR,
        /* 0x7c */  SHX::IUSHR, SHX::LUSHR, BIOP::IAND, BIOP::LAND,
        /* 0x80 */  BIOP::IOR, BIOP::LOR, BIOP::IXOR, BIOP::LXOR,
        /* 0x84 */  INC::IINC, X2Y::I2L, X2Y::I2F, X2Y::I2D,
        /* 0x88 */  X2Y::L2I, X2Y::L2F, X2Y::L2D, X2Y::F2I,
        /* 0x8c */  X2Y::F2L, X2Y::F2D, X2Y::D2I, X2Y::D2L,
        /* 0x90 */  X2Y::D2F, X2Y::I2B, X2Y::I2C, X2Y::I2S,
        /* 0x94 */  CMP::LCMP, CMP::FCMPL, CMP::FCMPG, CMP::DCMPL,
        /* 0x98 */  CMP::DCMPG, IF::IFEQ, IF::IFNE, IF::IFLT,
        /* 0x9c */  IF::IFGE, IF::IFGT, IF::IFLE, IF_ICMP::IF_ICMPEQ,
        /* 0xa0 */  IF_ICMP::IF_ICMPNE, IF_ICMP::IF_ICMPLT, IF_ICMP::IF_ICMPGE, IF_ICMP::IF_ICMPGT,
        /* 0xa4 */  IF_ICMP::IF_ICMPLE, IF_ACMP::IF_ACMPEQ, IF_ACMP::IF_ACMPNE, GOTO::new,
        /* 0xa8 */  null, null, null, null,
        /* 0xac */  RETURN::IRETURN, RETURN::LRETURN, RETURN::FRETURN, RETURN::DRETURN,
        /* 0xb0 */  RETURN::ARETURN, RETURN::RETURN, GETSTATIC::new, PUTSTATIC::new,
        /* 0xb4 */  GETFIELD::new, PUTFIELD::new, INVOKEVIRTUAL::new, INVOKESPECIAL::new,
        /* 0xb8 */  INVOKESTATIC::new, null, null, NEW::new,
        /* 0xbc */  NEWARRAY::NEWARRAY, NEWARRAY::ANEWARRAY, ARRAYLENGTH::new, ATHROW::new,
        /* 0xc0 */  CHECKCAST::new, INSTANCEOF::new, null, null,
        /* 0xc4 */  null, null, IF_NULL::IFNULL, IF_NULL::IFNONNULL,
        /* 0xc8 */  null, null, null, null,
        /* 0xcc */  null, null, null, null,
        /* 0xd0 */  null, null, null, null,
        /* 0xd4 */  null, null, null, null,
        /* 0xd8 */  null, null, null, null,
        /* 0xdc */  null, null, null, null,
        /* 0xe0 */  null, null, null, null,
        /* 0xe4 */  null, null, null, null,
        /* 0xe8 */  null, null, null, null,
        /* 0xec */  null, null, null, null,
        /* 0xf0 */  null, null, null, null,
        /* 0xf4 */  null, null, null, null,
        /* 0xf8 */  null, null, null, null,
        /* 0xfc */  null, null, null, null
        // @formatter:on
    );

}
