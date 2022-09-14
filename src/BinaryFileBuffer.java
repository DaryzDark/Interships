import java.io.*;

public class BinaryFileBuffer {
    public static int BUFFERSIZE = 2048;
    public BufferedReader reader;
    public File file;
    private final String sortmode;
    private final String datatype;
    private String readed;
    private String previouslyreaded = null;
    private boolean empty;

    public BinaryFileBuffer(File f, String sortmode, String datatype) {
        file = f;
        this.sortmode = sortmode;
        this.datatype = datatype;
        try {
            reader = new BufferedReader(new FileReader(f), BUFFERSIZE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        reload();
    }

    public boolean isOrdered() {
        if (previouslyreaded == null)
            return true;
        if (sortmode.equals("-a") && datatype.equals("-s"))
            return readed.compareTo(previouslyreaded) > 0;
        if (sortmode.equals("-d") && datatype.equals("-s"))
            return readed.compareTo(previouslyreaded) < 0;
        if (sortmode.equals("-a") && datatype.equals("-i")) {
            Integer i1 = Integer.parseInt(readed);
            Integer i2 = Integer.parseInt(previouslyreaded);
            return i1.compareTo(i2) > 0;
        }
        if (sortmode.equals("-d") && datatype.equals("-i")) {
            Integer i1 = Integer.parseInt(readed);
            Integer i2 = Integer.parseInt(previouslyreaded);
            return i1.compareTo(i2) < 0;
        }
        return true;

    }

    public boolean empty() {
        return empty;
    }

    private void reload() {
        try {
            this.readed = reader.readLine();
            if (!isOrdered()) throw new Exception("The elements are not in the right order");
            if (readed.indexOf(' ') != -1) throw new Exception("There are spaces");
            if(readed  == null || !isOrdered() || readed.indexOf(' ') != -1) {
                empty = true;
                readed = null;
            } else {
                previouslyreaded = readed;
                empty = false;
            }
        } catch(Exception e) {
            empty = true;
            readed = null;
        }
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getReaded() {
        if(empty())
            return null;
        return readed;
    }

    public String pop() {
        String answer = getReaded();
        reload();
        return answer;
    }
}
