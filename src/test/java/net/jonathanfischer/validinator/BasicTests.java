package net.jonathanfischer.validinator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.Test;

public class BasicTests {
	static final int NUM_ITERATIONS = 1000;

	final Random random = new Random();

	@Test
	public void strings() throws DecodeException {
		assertThat(Decoder.decodeString(""), is(""));
		assertThat(Decoder.decodeString("1234"), is("1234"));
	}

	@Test(expected = DecodeException.class)
	public void stringDecodeNullFails() throws DecodeException {
		Decoder.decodeString(null);
	}

	@Test(expected = DecodeException.class)
	public void stringDecodeBooleanFails() throws DecodeException {
		Decoder.decodeString(false);
	}

	@Test(expected = DecodeException.class)
	public void stringDecodeNumberFails() throws DecodeException {
		Decoder.decodeString(12345);
	}

	@Test(expected = DecodeException.class)
	public void stringDecodeObjectFails() throws DecodeException {
		Decoder.decodeString(new Object());
	}

	@Test
	public void booleans() throws DecodeException {
		assertThat(Decoder.decodeBoolean(false), is(false));
		assertThat(Decoder.decodeBoolean(true), is(true));
	}

	@Test(expected = DecodeException.class)
	public void booleanDecodeNullFails() throws DecodeException {
		Decoder.decodeBoolean(null);
	}

	@Test(expected = DecodeException.class)
	public void booleanDecodeNumberFails() throws DecodeException {
		Decoder.decodeBoolean(1123);
	}

	@Test(expected = DecodeException.class)
	public void booleanDecodeStringFails() throws DecodeException {
		Decoder.decodeBoolean("false");
	}

	@Test(expected = DecodeException.class)
	public void booleanDecodeObjectFails() throws DecodeException {
		Decoder.decodeBoolean(new Object());
	}

	@Test
	public void bytes() throws DecodeException {
		byte[] values = new byte[NUM_ITERATIONS];
		random.nextBytes(values);

		for (int i = 0; i < NUM_ITERATIONS; i++) {
			assertThat(Decoder.decodeByte(values[i]), is(values[i]));
		}
	}

	@Test(expected = DecodeException.class)
	public void byteDecodeNullFails() throws DecodeException {
		Decoder.decodeByte(null);
	}

	@Test(expected = DecodeException.class)
	public void byteDecodeOutOfRangeFails() throws DecodeException {
		Decoder.decodeByte(1123);
	}

	@Test(expected = DecodeException.class)
	public void byteDecodeStringFails() throws DecodeException {
		Decoder.decodeByte("false");
	}

	@Test(expected = DecodeException.class)
	public void byteDecodeObjectFails() throws DecodeException {
		Decoder.decodeByte(new Object());
	}

	@Test
	public void shorts() throws DecodeException {
		final int range = Short.MAX_VALUE - Short.MIN_VALUE;
		for (int i = 0; i < NUM_ITERATIONS; i++) {
			short value = (short)(random.nextInt(range) + Short.MIN_VALUE);
			assertThat(Decoder.decodeShort(value), is(value));
		}
	}

	@Test(expected = DecodeException.class)
	public void shortDecodeNullFails() throws DecodeException {
		Decoder.decodeShort(null);
	}

	@Test(expected = DecodeException.class)
	public void shortDecodeOutOfRangeFails() throws DecodeException {
		Decoder.decodeShort(Short.MAX_VALUE + 1);
	}

	@Test(expected = DecodeException.class)
	public void shortDecodeStringFails() throws DecodeException {
		Decoder.decodeShort("false");
	}

	@Test(expected = DecodeException.class)
	public void shortDecodeObjectFails() throws DecodeException {
		Decoder.decodeShort(new Object());
	}

	@Test
	public void ints() throws DecodeException {
		for (int i = 0; i < NUM_ITERATIONS; i++) {
			int value = random.nextInt();
			assertThat(Decoder.decodeInt(value), is(value));
		}
	}

	@Test(expected = DecodeException.class)
	public void intDecodeNullFails() throws DecodeException {
		Decoder.decodeInt(null);
	}

	@Test(expected = DecodeException.class)
	public void intDecodeOutOfRangeFails() throws DecodeException {
		Decoder.decodeInt((long)Integer.MAX_VALUE + 1L);
	}

	@Test(expected = DecodeException.class)
	public void intDecodeStringFails() throws DecodeException {
		Decoder.decodeInt("false");
	}

	@Test(expected = DecodeException.class)
	public void intDecodeObjectFails() throws DecodeException {
		Decoder.decodeInt(new Object());
	}

	@Test
	public void longs() throws DecodeException {
		for (int i = 0; i < NUM_ITERATIONS; i++) {
			long value = random.nextLong();
			assertThat(Decoder.decodeLong(value), is(value));
		}
	}

	@Test(expected = DecodeException.class)
	public void longDecodeNullFails() throws DecodeException {
		Decoder.decodeLong(null);
	}

	public void longDecodeOutOfRangeFails() throws DecodeException {
		long expected = Long.MAX_VALUE + 1;
		assertThat(Decoder.decodeLong((double)Long.MAX_VALUE + 1.0), is(not(expected)));
	}

	@Test(expected = DecodeException.class)
	public void longDecodeStringFails() throws DecodeException {
		Decoder.decodeLong("false");
	}

	@Test(expected = DecodeException.class)
	public void longDecodeObjectFails() throws DecodeException {
		Decoder.decodeLong(new Object());
	}

	@Test
	public void floats() throws DecodeException {
		for (int i = 0; i < NUM_ITERATIONS; i++) {
			float value = random.nextFloat();
			assertThat(Decoder.decodeFloat(value), is(value));
		}
	}

	@Test(expected = DecodeException.class)
	public void floatDecodeNullFails() throws DecodeException {
		Decoder.decodeFloat(null);
	}

	@Test
	public void floatDecodeOutOfRangeFails() throws DecodeException {
		assertThat(Decoder.decodeFloat(Double.MAX_VALUE), is(not(Double.MAX_VALUE)));
	}

	@Test(expected = DecodeException.class)
	public void floatDecodeStringFails() throws DecodeException {
		Decoder.decodeFloat("false");
	}

	@Test(expected = DecodeException.class)
	public void floatDecodeObjectFails() throws DecodeException {
		Decoder.decodeFloat(new Object());
	}

	@Test
	public void doubles() throws DecodeException {
		for (int i = 0; i < NUM_ITERATIONS; i++) {
			double value = random.nextDouble();
			assertThat(Decoder.decodeDouble(value), is(value));
		}
	}
	@Test(expected = DecodeException.class)
	public void doubleDecodeNullFails() throws DecodeException {
		Decoder.decodeDouble(null);
	}

	@Test(expected = DecodeException.class)
	public void doubleDecodeStringFails() throws DecodeException {
		Decoder.decodeDouble("false");
	}

	@Test(expected = DecodeException.class)
	public void doubleDecodeObjectFails() throws DecodeException {
		Decoder.decodeDouble(new Object());
	}

	@Test
	public void decodeListOfStrings() throws DecodeException {
		List<String> items = Arrays.asList("String 1", "String 2", "String 3");
		assertThat(Decoder.decodeList(items, Decoder.stringDecoder), is(items));
	}

	@Test
	public void decodeMixedList() throws DecodeException {
		List<Object> items = Arrays.asList("String", 1235, false, new Object());
		assertThat(Decoder.decodeList(items, Decoder.identityDecoder), is(items));
	}

	@Test(expected = DecodeException.class)
	public void decodeMixedListFails() throws DecodeException {
		List<Object> items = Arrays.asList("String", 1234, false);
		Decoder.decodeList(items, Decoder.stringDecoder);
	}
}
