import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileReader;

public class ReadFIFOJava {
   private static final String FIFO = "/tmp/testfifo";

   public static void main(String[] args){
//      BufferedReader in = null;
//      try{
//         System.out.println("JAVA SIDE!!");
//         in = new BufferedReader(new FileReader(FIFO));
//         while(in.ready()){
//             System.out.println(in.readLine());
//         }
//         //done, however you can choose to cycle over this line
//         //in this thread or launch another to check for new input
//         in.close();
//         System.out.println("JAVA CLOSE!!");
//      }catch(IOException ex){
//         System.err.println("IO Exception at buffered read!!");
//         System.exit(-1);
//      } catch(Exception e) {
//    	  	 System.err.println("Exception at buffered read!!");
//      }
	   
	   
	   try {
		   readNonStop();
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }

   }

   
 //PipeProcessor pipeProcessor; 
   
   private static void  readNonStop() throws IOException{
	   File  f_pipe = new File ( "/tmp/testfifo" );     
	   for(;;){
		   RandomAccessFile raf = new RandomAccessFile(f_pipe, "r");//p.1
		   String line=null;  
		   for( ;; ){
			   line=raf.readLine();                  
			   
			   //Take care to check the line -
			   //it is null when the pipe has no more available data. 
			   if( line!=null ) {
//                            pipeProcessor.process( line ); 
				   System.out.println(line);
			   }else{
				   break; //stop reading loop
			   }
		   }
		   //here - we got NULL line, re-open the RandomAccessFile  again 
	   }
   }

}
