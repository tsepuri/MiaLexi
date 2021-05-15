package com.csds393.mialexi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import java.io.BufferedWriter;
import com.csds393.mialexi.model.TextFile;

@RestController
@CrossOrigin
@RequestMapping("/textInput")
public class TextInputController {
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
    @GetMapping(value = "/wikipedia/{pageName}")
    public String getWikipedia(@PathVariable final String pageName) {
        try {
			String wikipediaApiJSON = "https://www.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="
					+ URLEncoder.encode(pageName, "UTF-8");
			HttpURLConnection httpcon = (HttpURLConnection) new URL(wikipediaApiJSON).openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
			String responseSB = in.lines().collect(Collectors.joining());
			in.close();
			String result = responseSB.split("extract\":\"")[1];
			Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
			Matcher m = p.matcher(result);
			StringBuffer buf = new StringBuffer(result.length());
			while (m.find()) {
				String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
				m.appendReplacement(buf, Matcher.quoteReplacement(ch));
			}
			m.appendTail(buf);
			result = buf.toString();
			result = result.replaceAll("\\\\n", " ").replaceAll("}", " ").replaceAll("\\\\", "");
            return result.trim().substring(0, result.trim().length() - 1);
        } catch(Exception e) {
            return "An error occured while getting the Wikipedia page";
        }
    }
    @PostMapping("/file")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String path = uploadFileService(file);
        if (path.contains(".pdf")) {
            return pdfToString(path);
        }
        else {
            return txtToString(path);
        }
    }
    public String pdfToString(String filename) throws IOException {
        String parsedText = "";
        File file = new File(filename);
        PDDocument pdDoc = Loader.loadPDF(file);
       PDFTextStripper pdfStripper = new PDFTextStripper();
        parsedText = pdfStripper.getText(pdDoc);
        
        //PrintWriter pw = new PrintWriter("C:\\Users\\hjw61\\IdeaProjects\\miaLexi\\test.txt");
        //pw.print(parsedText);
        //pw.close();
        //System.out.print(parsedText);
        return parsedText;
    }

    public String txtToString(String filename) throws FileNotFoundException {
        File file = new File(filename);
		Scanner scan = new Scanner(file);
        String output = "";
        while(scan.hasNext()){
            output += scan.next();
            output += " ";
		}

        return output;

    }

    public String uploadFileService(MultipartFile file) throws Exception {

        try {
            Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            return copyLocation.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }
	
}   