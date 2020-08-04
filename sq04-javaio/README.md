- Input streams = Bytearrayinputstream, PipeInputStreams FIleInputStreams

- OutPut streams = BytearrayOutPutstream, PipeOutPutStreams FIleOutPutStreams


IO stream and streams api


### A stream is an ordered sequence of data that...

- ### Provides a common I/O model
- ### Abstracts details from an underlying source or destination

Stream are unidirectional. You either read from a stream or write to a stream

there are two types of data written/read from a stream. bytes or chars

# InputStreams = reads bytes;
# OutputStreams = writes bytes;

======================================

# Reader = reads chars
# Writer = writes chars

======================================


Input streams = Bytearrayinputstream, PipeInputStreams FIleInputStreams

OutPut streams = BytearrayOutPutstream, PipeOutPutStreams FIleOutPutStreams

========================================

Reader = CharArrayReader, StringReader, Piped Reader, InputStreamReader (FileReader underneath)


Writer  = CharArrayWriter , StringWriter , Piped Writer , InputStreamWriter (FileWriter underneath)



Handling errors with autoclosable and closeable interfaces

- FileReader
- FileWriter 
- FileInputStream 
- FileOutputStream

Replace with:

- BufferReader 
- BufferWriter 
- BufferedInputStream 
- BufferedOutputStream 

write and readAllLines