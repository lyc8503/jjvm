package vjvm.interpreter.instruction;

import java.util.function.BiFunction;

import lombok.var;
import vjvm.interpreter.instruction.constants.XCONST_Y;
import vjvm.interpreter.instruction.constants.XPUSH;
import vjvm.interpreter.instruction.references.INVOKESTATIC;
import vjvm.interpreter.instruction.control.XRETURN;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.UnimplementedInstructionError;

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
      /* 0x08 */  XCONST_Y::ICONST_5, null, null, null,
      /* 0x0c */  null, null, null, null,
      /* 0x10 */  XPUSH::BIPUSH, XPUSH::SIPUSH, null, null,
      /* 0x14 */  null, null, null, null,
      /* 0x18 */  null, null, null, null,
      /* 0x1c */  null, null, null, null,
      /* 0x20 */  null, null, null, null,
      /* 0x24 */  null, null, null, null,
      /* 0x28 */  null, null, null, null,
      /* 0x2c */  null, null, null, null,
      /* 0x30 */  null, null, null, null,
      /* 0x34 */  null, null, null, null,
      /* 0x38 */  null, null, null, null,
      /* 0x3c */  null, null, null, null,
      /* 0x40 */  null, null, null, null,
      /* 0x44 */  null, null, null, null,
      /* 0x48 */  null, null, null, null,
      /* 0x4c */  null, null, null, null,
      /* 0x50 */  null, null, null, null,
      /* 0x54 */  null, null, null, null,
      /* 0x58 */  null, null, null, null,
      /* 0x5c */  null, null, null, null,
      /* 0x60 */  null, null, null, null,
      /* 0x64 */  null, null, null, null,
      /* 0x68 */  null, null, null, null,
      /* 0x6c */  null, null, null, null,
      /* 0x70 */  null, null, null, null,
      /* 0x74 */  null, null, null, null,
      /* 0x78 */  null, null, null, null,
      /* 0x7c */  null, null, null, null,
      /* 0x80 */  null, null, null, null,
      /* 0x84 */  null, null, null, null,
      /* 0x88 */  null, null, null, null,
      /* 0x8c */  null, null, null, null,
      /* 0x90 */  null, null, null, null,
      /* 0x94 */  null, null, null, null,
      /* 0x98 */  null, null, null, null,
      /* 0x9c */  null, null, null, null,
      /* 0xa0 */  null, null, null, null,
      /* 0xa4 */  null, null, null, null,
      /* 0xa8 */  null, null, null, null,
      /* 0xac */  null, null, null, null,
      /* 0xb0 */  null, XRETURN::RETURN, null, null,
      /* 0xb4 */  null, null, null, null,
      /* 0xb8 */  INVOKESTATIC::new, null, null, null,
      /* 0xbc */  null, null, null, null,
      /* 0xc0 */  null, null, null, null,
      /* 0xc4 */  null, null, null, null,
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
