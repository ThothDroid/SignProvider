package com.blueapps.signprovider;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class SignProvider {

    private static final String TAG = "SignProvider";

    private Context context;

    // Constants
    // Database Data
    public static final String FILENAME_DRAWABLE_IDS = "Databases/Drawable_Ids.csv";
    public static final String FILENAME_DRAWABLE_PATHS = "Databases/Drawable_Paths.csv";
    public static final String PATH_PREFIX_BIN_IMAGES = "assets/Unicode/bin/";
    public static final String PATH_PREFIX_PATH_DATA = "Unicode/path/";
    public static final String NOT_FOUND_DATA = "m164.84,83.21q0,12.86 -4.59,22.97 -4.59,9.98 -12.08,17.72 -7.35,7.48 -16.93,14.04 -9.58,6.56 -20.34,12.73v29.53H87.4v-40.03q8.53,-4.86 18.38,-10.63 9.98,-5.78 16.28,-11.68 7.61,-6.83 11.81,-14.04 4.2,-7.35 4.2,-18.64 0,-14.83 -10.11,-22.05 -9.98,-7.35 -25.86,-7.35 -14.18,0 -26.91,4.46 -12.6,4.46 -19.95,9.06H53.93V42.52q9.19,-3.54 23.23,-6.3 14.18,-2.89 26.78,-2.89 28.22,0 44.5,13.78 16.41,13.65 16.41,36.1zM113,232.71H86.22V205.02H113ZM9.84,12.3H199.23V249.05H9.84Z";


    public SignProvider(Context context){
        this.context = context;
    }


    public ArrayList<String> getAllSigns() throws IOException {
        ArrayList<String> returnArray = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open(FILENAME_DRAWABLE_PATHS)));
            String[] line;
            while ((line = reader.readNext()) != null) {
                for (String field : line) {
                    String[] row = field.split(";");
                    String id = row[0];
                    returnArray.add(id);
                }
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        return returnArray;
    }

    public Drawable getSign(String Id) throws IOException, XmlPullParserException {

        Drawable signDrawable = null;

        // get the filename of the drawable
        String drawableFileName;
        drawableFileName = getDrawableFilePath(Id);

        Drawable drawable;
        try {
            drawable = getXMLDrawable(drawableFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            drawable = ContextCompat.getDrawable(context, R.drawable.not_found_sign);
        }

        if (drawableFileName.isEmpty()) {
            drawable = ContextCompat.getDrawable(context, R.drawable.not_found_sign);
        }

        signDrawable = drawable;

        return signDrawable;

    }

    /**
     * Retrieves the path data for a given sign ID.
     * @param Id The sign ID for which to retrieve the path data. Phonetics allowed.
     * @return The path data as a String. If the sign ID is not found, returns the data of Not Found Sign.
     * @throws IOException error reading the database files
     */
    public SvgData getSvgData(String Id) throws IOException {

        StringBuilder pathData = new StringBuilder();
        String width = "0";
        String height = "0";

        // get the filename of the drawable
        String drawableFileName;
        drawableFileName = getDrawableFileName(Id);

        // extract pathData
        // read path file from assets and return the content as a string
        try {
            InputStream is = context.getAssets().open(PATH_PREFIX_PATH_DATA + drawableFileName + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            // read content line by line
            String line = reader.readLine();
            while (line != null) {
                pathData.append(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + PATH_PREFIX_PATH_DATA + drawableFileName + ".txt");
            pathData = new StringBuilder(NOT_FOUND_DATA);
        }

        if (drawableFileName.isEmpty()) {
            pathData = new StringBuilder(NOT_FOUND_DATA);
        }

        return new SvgData(pathData.toString(), width, height);

    }

    private String getDrawableFileName(String id) throws IOException {
        Log.i(TAG, "Sign: id=" + id);

        String alternativeId = getGardinerFromPhonetic(id);
        String name;

        CSVReader PathReader = new CSVReader(new InputStreamReader(context.getAssets().open(FILENAME_DRAWABLE_PATHS)));

        if (alternativeId.isEmpty()){

            name = search(PathReader, id, false);

        } else {

            name = search(PathReader, alternativeId, false);
            Log.d(TAG, alternativeId);

        }

        return name;
    }

    private String getDrawableFilePath(String id) throws IOException {
        Log.i(TAG, "Sign: id=" + id);

        String alternativeId = getGardinerFromPhonetic(id);
        String path;

        CSVReader PathReader = new CSVReader(new InputStreamReader(context.getAssets().open(FILENAME_DRAWABLE_PATHS)));

        if (alternativeId.isEmpty()){

            path = search(PathReader, id, true);

        } else {

            path = search(PathReader, alternativeId, true);
            Log.d(TAG, alternativeId);

        }

        if (Objects.equals(path, "")){
            return "";
        } else {
            path = path + ".xml";
            return path;
        }
    }

    private Drawable getXMLDrawable(String fileName) throws IOException, XmlPullParserException {
        XmlResourceParser parser = context.getAssets().openXmlResourceParser(fileName);
        return VectorDrawableCompat.createFromXml(context.getResources(), parser);
    }


    public String getGardinerFromPhonetic(String phonetic) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open(FILENAME_DRAWABLE_IDS)));
        String gardiner = search(reader, phonetic, false);
        if (!gardiner.isEmpty()) {
            return gardiner;
        }
        return phonetic;
    }

    public ArrayList<String> getPhoneticsFromGardiner(String gardiner) throws IOException {
        ArrayList<String> phonetics = new ArrayList<>();

        CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open(FILENAME_DRAWABLE_IDS)));
        try {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                for (String field : nextLine) {
                    String[] row = field.split(";");
                    if (row.length > 1) {
                        if (row[1].matches(gardiner)) {
                            Log.i(TAG, "Phonetic: " + row[0]);
                            phonetics.add(row[0]);
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return phonetics;
    }


    private static String search(CSVReader reader, String s, boolean fullPath) throws IOException {
        try {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                for (String field : nextLine) {
                    String[] row = field.split(";");
                    if (row[0].matches(s)) {
                        Log.i(TAG, "Found: " + field);
                        if (row.length > 1) {
                            Log.i(TAG, "Sign: " + row[1]);
                            if (fullPath) {
                                return PATH_PREFIX_BIN_IMAGES + row[1];
                            } else {
                                return row[1];
                            }
                        }
                    }
                }
            }
        } catch (PatternSyntaxException | CsvValidationException e){
            e.printStackTrace();
        }
        return "";
    }

}