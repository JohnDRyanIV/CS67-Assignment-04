import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 
 */
public class Assignment4 {
	
	public static void main(String[] args) {
	
		File myFile = new File("Assignment4Input.txt");
        try {
			Scanner inputFile = new Scanner(myFile);
			
			String first = "";
			String second = "";
			String third = "";
			String current = "";
			String fileString = "";
			
			int length = 0;
			// For larger file sizes, modify program to use array of strings
			
			if(inputFile.hasNext()) {
				// Gathering string data from file
				while(inputFile.hasNext()) {
					fileString += inputFile.next();
				}
				
				length = fileString.length();
				for(int i = 0; i < length; i++) {
					current = fileString.substring(i, i+1);
					if(current.equals("X") && (i+4 < length)) {
						first += fileString.substring(i+3, i+4);
					}
					else if (current.equals("Y") && (i+6 < length)) {
						second += fileString.substring(i+5, i+6);
					}
					else if (current.equals("W") && (i+5 < length)) {
						third += fileString.substring(i+4, i+5);
					}
				}
				
				System.out.println(first);
				System.out.println(second);
				System.out.println(third);
			}
			
			else {
				System.out.println("File is empty");
			}
			
			inputFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist.");
			e.printStackTrace();
		}

        try {
            checkMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public static void checkMessage() throws Exception {
        String toCheck = "https://pastebin.com/raw/mvJarZnh";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(toCheck))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
