package com.compression.lz4compression;

import java.io.File;
import java.time.Instant;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class Test {
   public static void main(String args[]) {
	   
	   LZ4Factory factory = LZ4Factory.fastestInstance();
	   File file = new File("/Users/amitsaluja/WORK/large-file.json");
	   byte[] bytes = new byte[(int) file.length()];
       System.out.print(bytes.length);
        Long decompressedLength = file.length();
       Test obj=new Test();
       Long start=System.currentTimeMillis();
       bytes= obj.compress(bytes, factory);
       Long end=System.currentTimeMillis();
       System.out.println("Compression Time="+(end-start)+"ms");
       Long start1=System.currentTimeMillis();
       
       obj.deCompress(bytes, factory, Integer.parseInt(decompressedLength.toString()));
       Long end1=System.currentTimeMillis();
       
       System.out.println("DeCompression Time="+(end1-start1)+"ms");
       
         
	 
   }
   
   public  byte[] compress(byte[] data,LZ4Factory factory) {
	   
	   final int decompressedLength = data.length;
	   // compress data
	   LZ4Compressor compressor = factory.fastCompressor();
	   int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
	   byte[] compressed = new byte[maxCompressedLength];
	   int compressedLength = compressor.compress(data, 0, decompressedLength, compressed, 0, maxCompressedLength);
	   System.out.println("Compresssed length ="+compressedLength);
        
	   return compressed;
   }
   
   public  void deCompress(byte[] compressed,LZ4Factory factory,int decompressedLength) {
	              LZ4FastDecompressor decompressor = factory.fastDecompressor(); byte[]
				  restored = new byte[decompressedLength]; 
	              int compressedLength2 =
				  decompressor.decompress(compressed, 0, restored, 0, decompressedLength);
	              
	              System.out.println("DeCompresssed length ="+compressedLength2);
	              
   }
   

}
