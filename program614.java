
//unpaking code
import java.io.*;
import java.util.*;

class program614 {
    public static void main(String A[]) throws Exception {

        // variable creation
        int FileSize = 0;
        int i = 0;
        int iRet = 0;

        byte Key = 0x11;

        Scanner sobj = null;
        String FileName = null;

        File fpackobj = null;
        File fobj = null;

        FileInputStream fiobj = null;
        FileOutputStream foobj = null;
        byte bHeader[] = new byte[100];
        byte Buffer[] = null;

        String Header = null;
        String Tokens[] = null;

        sobj = new Scanner(System.in);

        System.out.println("enter the name of pack file:");
        FileName = sobj.nextLine();

        fpackobj = new File(FileName);

        if (fpackobj.exists() == false) {
            System.out.println("Errotr:There is no such packed file");
            return;
        }

        fiobj = new FileInputStream(fpackobj);

        // Read the header
        while ((iRet = fiobj.read(bHeader, 0, 100)) != -1) {

            Header = new String(bHeader);

            Header = Header.trim();

            Tokens = Header.split(" ");

            System.out.println("Filename:" + Tokens[0]);
            System.out.println("FileSize:" + Tokens[1]);

            fobj = new File(Tokens[0]);

            fobj.createNewFile();

            foobj = new FileOutputStream(fobj);

            FileSize = Integer.parseInt(Tokens[1]);

            // buffer for redaing the data
            Buffer = new byte[FileSize];

            // read from packed file
            fiobj.read(Buffer, 0, FileSize);

            // Decrypted the data
            for (i = 0; i < FileSize; i++) {
                Buffer[i] = (byte) (Buffer[1] ^ Key);
            }
            // write into extracted file
            foobj.write(Buffer, 0, FileSize);

        }

    }
}