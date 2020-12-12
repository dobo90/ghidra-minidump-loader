package net.jubjubnest.minidump.parser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import ghidra.app.util.bin.ByteProvider;

public class Memory64Descriptor {

	public static final int RECORD_SIZE = 8 + 8;

	public static Memory64Descriptor parse(long offset, ByteProvider provider, long dataOffset) throws IOException {
		var bytes = provider.readBytes(offset, RECORD_SIZE);
		var byteBuffer = ByteBuffer.wrap(bytes);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		return parse(byteBuffer, dataOffset);
	}

	public static Memory64Descriptor parse(ByteBuffer byteBuffer, long dataOffset) {
		var descriptor = new Memory64Descriptor();
		descriptor.baseAddress = byteBuffer.getLong();
		descriptor.segmentSize = byteBuffer.getLong();
		descriptor.dataOffset = dataOffset;
		return descriptor;
	}

	public long baseAddress;
	public long segmentSize;
	public long dataOffset;
}
