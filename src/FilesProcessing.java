import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FilesProcessing {
    private final String sortmode;
    private final String datatype;
    private final File outputfile;
    private  final List<File> filelist;

    public FilesProcessing(String[] args) {
        int flag = 1;
        if (!args[0].equals("-a") && !args[0].equals("-d")) {
            this.sortmode = "-a";
            flag = 0;
        } else
            this.sortmode = args[0];
        this.datatype = args[flag];
        this.outputfile = new File(args[flag + 1]);
        if (!outputfile.isFile()) throw new RuntimeException("Wrong console arguments or file is missing");
        this.filelist = new ArrayList<>();
        for (int i = flag + 2; i < args.length; i++)
            filelist.add(new File(args[i]));
    }

    /**
     * The method creates a comparator depending on the input parameters
     * @return comparator depending on the input parameters
     */
    public Comparator<String> getComporator() {
        if (sortmode.equals("-a") && datatype.equals("-s"))
            return  new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            };
        if (sortmode.equals("-a") && datatype.equals("-i"))
            return  new Comparator<String>() {
                public int compare(String o1, String o2) {
                    Integer i1 = Integer.parseInt(o1);
                    Integer i2 = Integer.parseInt(o2);
                    return i1.compareTo(i2);
                }
            };
        if (sortmode.equals("-d") && datatype.equals("-s"))
            return  new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2) * -1;
                }
            };
        if (sortmode.equals("-d") && datatype.equals("-i"))
            return  new Comparator<String>() {
                public int compare(String o1, String o2) {
                    Integer i1 = Integer.parseInt(o1);
                    Integer i2 = Integer.parseInt(o2);
                    return i1.compareTo(i2) * -1;
                }
            };
        return null;
    }

    /**
     * Static method that merges sorted files
     * @param filelist
     * @param outputfile
     * @param cmp
     * @param sortmode
     * @param datatype
     */
    public static void mergeFiles(List<File> filelist, File outputfile, final Comparator<String> cmp, String sortmode, String datatype) {

        Comparator<BinaryFileBuffer> buffercomporator = new Comparator<BinaryFileBuffer>() {
            @Override
            public int compare(BinaryFileBuffer o1, BinaryFileBuffer o2) {
                return cmp.compare(o1.getReaded(),o2.getReaded());
            }
        };

        PriorityQueue<BinaryFileBuffer> queue = new PriorityQueue<>(buffercomporator);
        for (File file : filelist) {
            BinaryFileBuffer buffer = new BinaryFileBuffer(file, sortmode, datatype);
            if (!buffer.empty())
                queue.add(buffer);
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(outputfile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (queue.size() > 0) {
                BinaryFileBuffer buffer = queue.poll();
                String str = buffer.pop();
                try {
                    writer.write(str);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (buffer.empty()) {
                    try {
                        buffer.reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    queue.add(buffer);
                }
            }
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            for (BinaryFileBuffer buffer : queue) {
                buffer.close();
            }

        }

    public String getSortmode() {
        return this.sortmode;
    }

    public List<File> getFilelist() {
        return filelist;
    }

    public File getOutputfile() {
        return outputfile;
    }

    public String getDatatype() {
        return datatype;
    }
}
