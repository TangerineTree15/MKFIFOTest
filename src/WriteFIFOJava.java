import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteFIFOJava {

	
	public WriteFIFOJava(){
		
	}
	private static void  readNonStop() throws IOException{
		File  f_pipe = new File ( "/tmp/testfifo" );     
//		for(;;){
			RandomAccessFile raf = new RandomAccessFile(f_pipe, "rw");//p.1
			String line="123\n";
			
			raf.writeUTF(line);
			
//			for( ;; ){
//				line=raf.readLine();                  
//				
//				//Take care to check the line -
//				//it is null when the pipe has no more available data. 
//				if( line!=null ) {
////	                            pipeProcessor.process( line ); 
//					System.out.println(line);
//				}else{
//					break; //stop reading loop
//				}
//			}
			//here - we got NULL line, re-open the RandomAccessFile  again 
//		}
	}
	
	   
	public static void main(String[] args){
		try {
			readNonStop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 
}