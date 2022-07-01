package vjvm.interpreter.instruction;

import lombok.var;
import vjvm.interpreter.instruction.comparisons.IF;
import vjvm.interpreter.instruction.comparisons.IF_ICMP;
import vjvm.interpreter.instruction.comparisons.XCMP;
import vjvm.interpreter.instruction.constants.LDC;
import vjvm.interpreter.instruction.constants.XCONST_Y;
import vjvm.interpreter.instruction.constants.XPUSH;
import vjvm.interpreter.instruction.control.GOTO;
import vjvm.interpreter.instruction.control.XRETURN;
import vjvm.interpreter.instruction.conversions.X2Y;
import vjvm.interpreter.instruction.loads.XLOAD_Y;
import vjvm.interpreter.instruction.math.*;
import vjvm.interpreter.instruction.references.INVOKESTATIC;
import vjvm.interpreter.instruction.reserved.BREAKPOINT;
import vjvm.interpreter.instruction.stack.DUP;
import vjvm.interpreter.instruction.stack.POP;
import vjvm.interpreter.instruction.stack.SWAP;
import vjvm.interpreter.instruction.stores.XSTORE_Y;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.UnimplementedInstructionError;

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
        /* 0x00 */  null, null, XCONST_Y::ICONST_M1, XCONST_Y::ICONST_0,
        /* 0x04 */  XCONST_Y::ICONST_1, XCONST_Y::ICONST_2, XCONST_Y::ICONST_3, XCONST_Y::ICONST_4,
        /* 0x08 */  XCONST_Y::ICONST_5, XCONST_Y::LCONST_0, XCONST_Y::LCONST_1, XCONST_Y::FCONST_0,
        /* 0x0c */  XCONST_Y::FCONST_1, XCONST_Y::FCONST_2, XCONST_Y::DCONST_0, XCONST_Y::DCONST_1,
        /* 0x10 */  XPUSH::BIPUSH, XPUSH::SIPUSH, LDC::LDC, LDC::LDC_W,
        /* 0x14 */  LDC::LDC2_W, XLOAD_Y::ILOAD, XLOAD_Y::LLOAD, XLOAD_Y::FLOAD,
        /* 0x18 */  XLOAD_Y::DLOAD, null, XLOAD_Y::ILOAD_0, XLOAD_Y::ILOAD_1,
        /* 0x1c */  XLOAD_Y::ILOAD_2, XLOAD_Y::ILOAD_3, XLOAD_Y::LLOAD_0, XLOAD_Y::LLOAD_1,
        /* 0x20 */  XLOAD_Y::LLOAD_2, XLOAD_Y::LLOAD_3, XLOAD_Y::FLOAD_0, XLOAD_Y::FLOAD_1,
        /* 0x24 */  XLOAD_Y::FLOAD_2, XLOAD_Y::FLOAD_3, XLOAD_Y::DLOAD_0, XLOAD_Y::DLOAD_1,
        /* 0x28 */  XLOAD_Y::DLOAD_2, XLOAD_Y::DLOAD_3, null, null,
        /* 0x2c */  null, null, null, null,
        /* 0x30 */  null, null, null, null,
        /* 0x34 */  null, null, XSTORE_Y::ISTORE, XSTORE_Y::LSTORE,
        /* 0x38 */  XSTORE_Y::FSTORE, XSTORE_Y::DSTORE, null, XSTORE_Y::ISTORE_0,
        /* 0x3c */  XSTORE_Y::ISTORE_1, XSTORE_Y::ISTORE_2, XSTORE_Y::ISTORE_3, XSTORE_Y::LSTORE_0,
        /* 0x40 */  XSTORE_Y::LSTORE_1, XSTORE_Y::LSTORE_2, XSTORE_Y::LSTORE_3, XSTORE_Y::FSTORE_0,
        /* 0x44 */  XSTORE_Y::FSTORE_1, XSTORE_Y::FSTORE_2, XSTORE_Y::FSTORE_3, XSTORE_Y::DSTORE_0,
        /* 0x48 */  XSTORE_Y::DSTORE_1, XSTORE_Y::DSTORE_2, XSTORE_Y::DSTORE_3, null,
        /* 0x4c */  null, null, null, null,
        /* 0x50 */  null, null, null, null,
        /* 0x54 */  null, null, null, POP::POP,
        /* 0x58 */  POP::POP2, DUP::DUP, DUP::DUP_X1, DUP::DUP_X2,
        /* 0x5c */  DUP::DUP2, DUP::DUP2_X1, DUP::DUP2_X2, SWAP::new,
        /* 0x60 */  ADD::IADD, ADD::LADD, ADD::FADD, ADD::DADD,
        /* 0x64 */  SUB::ISUB, SUB::LSUB, SUB::FSUB, SUB::DSUB,
        /* 0x68 */  MUL::IMUL, MUL::LMUL, MUL::FMUL, MUL::DMUL,
        /* 0x6c */  DIV::IDIV, DIV::LDIV, DIV::FDIV, DIV::DDIV,
        /* 0x70 */  REM::IREM, REM::LREM, REM::FREM, REM::DREM,
        /* 0x74 */  NEG::INEG, NEG::LNEG, NEG::FNEG, NEG::DNEG,
        /* 0x78 */  SHL::ISHL, SHL::LSHL, SHR::ISHR, SHR::LSHR,
        /* 0x7c */  USHR::IUSHR, USHR::LUSHR, AND::IAND, AND::LAND,
        /* 0x80 */  OR::IOR, OR::LOR, XOR::IXOR, XOR::LXOR,
        /* 0x84 */  INC::IINC, X2Y::I2L, X2Y::I2F, X2Y::I2D,
        /* 0x88 */  X2Y::L2I, X2Y::L2F, X2Y::L2D, X2Y::F2I,
        /* 0x8c */  X2Y::F2L, X2Y::F2D, X2Y::D2I, X2Y::D2L,
        /* 0x90 */  X2Y::D2F, X2Y::I2B, X2Y::I2C, X2Y::I2S,
        /* 0x94 */  XCMP::LCMP, XCMP::FCMPL, XCMP::FCMPG, XCMP::DCMPL,
        /* 0x98 */  XCMP::FCMPG, IF::IFEQ, IF::IFNE, IF::IFLT,
        /* 0x9c */  IF::IFGE, IF::IFGT, IF::IFLE, IF_ICMP::IF_ICMPEQ,
        /* 0xa0 */  IF_ICMP::IF_ICMPNE, IF_ICMP::IF_ICMPLT, IF_ICMP::IF_ICMPGE, IF_ICMP::IF_ICMPGT,
        /* 0xa4 */  IF_ICMP::IF_ICMPLE, null, null, GOTO::new,
        /* 0xa8 */  null, null, null, null,
        /* 0xac */  XRETURN::IRETURN, XRETURN::LRETURN, XRETURN::FRETURN, XRETURN::DRETURN,
        /* 0xb0 */  null, XRETURN::RETURN, null, null,
        /* 0xb4 */  null, null, null, null,
        /* 0xb8 */  INVOKESTATIC::new, null, null, null,
        /* 0xbc */  null, null, null, null,
        /* 0xc0 */  null, null, null, null,
        /* 0xc4 */  null, null, null, null,
        /* 0xc8 */  null, null, BREAKPOINT::new, null,
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
