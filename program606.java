
//Packing Code
import java.io.*;
import java.util.*;

class program606 {
    public static void main(String A[]) throws Exception {
        String Header = null;

        byte Key = 0x11;

        int iRet = 0;
        int i = 0, j = 0;

        byte Buffer[] = new byte[1024];
        byte bHeader[] = new byte[100];

        Scanner sobj = new Scanner(System.in);

        System.out.println("Enter the name of floder");
        String FloderName = sobj.nextLine();

        System.out.println("Enter the name of packedfile");
        String PackName = sobj.nextLine();

        File fobj = new File(FloderName);

        if ((fobj.exists()) && (fobj.isDirectory())) {
            File packobj = new File(PackName);

            packobj.createNewFile();

            FileOutputStream foobj = new FileOutputStream(packobj);

            FileInputStream fiobj = null;

            System.out.println("Floder is present");

            File fArr[] = fobj.listFiles();

            System.out.println("number of files in the floder" + fArr.length);
            for (i = 0; i < fArr.length; i++) {
                fiobj = new FileInputStream(fArr[i]);

                if (fArr[i].getName().endsWith(".txt")) {
                    // header formation
                    Header = fArr[i].getName() + " " + fArr[i].length();

                    for (j = Header.length(); j < 100; j++) {
                        Header = Header + " ";
                    }
                    bHeader = Header.getBytes();

                    // write hedaer into pack file
                    foobj.write(bHeader, 0, 100);

                    // Reda the data from inputs files from Marvellous floder
                    while ((iRet = fiobj.read(Buffer)) != -1) {
                        // Encryption logic
                        for (j = 0; j < iRet; j++) {
                            Buffer[j] = (byte) (Buffer[j] ^ Key);
                        }
                        // Write the files data into pack file
                        foobj.write(Buffer, 0, iRet);

                    }

                }

                fiobj.close();

            }
            foobj.close();
        } else {
            System.out.println("there is no such floder");
        }

    }
}