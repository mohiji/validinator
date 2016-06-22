package net.jonathanfischer.validinator;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Decoder {
	public static final IDecoder<String> stringDecoder = new IDecoder<String>() {
		@Override
		public String decode(Object o) throws DecodeException {
			if (o == null) throw new DecodeException("Input object was null");


			try {
				return (String)o;
			}
			catch (ClassCastException e) {
				throw new DecodeException(e);
			}
		}
	};

	public static final IDecoder<Boolean> booleanDecoder = new IDecoder<Boolean>() {
		@Override
		public Boolean decode(Object o) throws DecodeException {
			if (o == null) throw new DecodeException("Input object was null");

			try {
				return (Boolean)o;
			}
			catch (ClassCastException e) {
				throw new DecodeException(e);
			}
		}
	};

	public static final IDecoder<Number> numberDecoder = new IDecoder<Number>() {
		@Override
		public Number decode(Object o) throws DecodeException {
			if (o == null) throw new DecodeException("Input object was null");

			try {
				return (Number)o;
			}
			catch (ClassCastException e) {
				throw new DecodeException(e);
			}
		}
	};

	public static final IDecoder<Byte> byteDecoder = new IDecoder<Byte>() {
		@Override
		public Byte decode(Object o) throws DecodeException {
			long value = longDecoder.decode(o);
			if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
				throw new DecodeException("Byte value out of range.");
			}

			return (byte)value;
		}
	};

	public static final IDecoder<Short> shortDecoder = new IDecoder<Short>() {
		@Override
		public Short decode(Object o) throws DecodeException {
			long value = longDecoder.decode(o);
			if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
				throw new DecodeException("Short value out of range.");
			}

			return (short)value;
		}
	};

	public static final IDecoder<Integer> integerDecoder = new IDecoder<Integer>() {
		@Override
		public Integer decode(Object o) throws DecodeException {
			long value = longDecoder.decode(o);
			if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
				throw new DecodeException("Integer value out of range.");
			}

			return (int)value;
		}
	};

	public static final IDecoder<Long> longDecoder = new IDecoder<Long>() {
		@Override
		public Long decode(Object o) throws DecodeException {
			return numberDecoder.decode(o).longValue();
		}
	};

	public static final IDecoder<Float> floatDecoder = new IDecoder<Float>() {
		@Override
		public Float decode(Object o) throws DecodeException {
			return numberDecoder.decode(o).floatValue();
		}
	};

	public static final IDecoder<Double> doubleDecoder = new IDecoder<Double>() {
		@Override
		public Double decode(Object o) throws DecodeException {
			return numberDecoder.decode(o).doubleValue();
		}
	};

	public static final IDecoder<Object> identityDecoder = new IDecoder<Object>() {
		@Override
		public Object decode(Object o) throws DecodeException {
			if (o == null) throw new DecodeException("Nulls are not supported, even by the identity function.");
			return o;
		}
	};

	public static String decodeString(Object o) throws DecodeException {
		return stringDecoder.decode(o);
	}

	public static boolean decodeBoolean(Object o) throws DecodeException {
		return booleanDecoder.decode(o);
	}

	public static byte decodeByte(Object o) throws DecodeException {
		return byteDecoder.decode(o);
	}

	public static short decodeShort(Object o) throws DecodeException {
		return shortDecoder.decode(o);
	}

	public static int decodeInt(Object o) throws DecodeException {
		return integerDecoder.decode(o);
	}

	public static long decodeLong(Object o) throws DecodeException {
		return longDecoder.decode(o);
	}

	public static float decodeFloat(Object o) throws DecodeException {
		return floatDecoder.decode(o);
	}

	public static double decodeDouble(Object o) throws DecodeException {
		return doubleDecoder.decode(o);
	}

	public static <T> List<T> decodeList(Object o, IDecoder<T> decoder) throws DecodeException {
		try {
			Iterable source = (Iterable)o;
			List<T> items = new ArrayList<T>();

			Iterator i = source.iterator();
			while (i.hasNext()) {
				items.add(decoder.decode(i.next()));
			}

			return items;
		}
		catch (ClassCastException e) {
			throw new DecodeException(e);
		}
	}
}
