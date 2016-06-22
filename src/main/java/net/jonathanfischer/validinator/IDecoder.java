package net.jonathanfischer.validinator;

public interface IDecoder<T> {
	T decode(Object o) throws DecodeException;
}

