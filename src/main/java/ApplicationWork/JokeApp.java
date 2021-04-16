package ApplicationWork;

import java.util.Scanner;

//import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import com.techelevator.tenmo.model.Root;


public class JokeApp {

	//public JokeApp(DataSource datasource) {
		//venueDAO = new JDBCVenueDAO(datasource);
	//}
	
	// STEP 2: Instantiate a JdbcTemplatae and pass it the DataSource
	/* The JdbcTemplate is the main interface we use to interact with databases using
	 * Spring JDBC. */
	
	//JdbcTemplate dvdstoreJdbcTemplate = new JdbcTemplate(dvdstoreDataSource);
	

//	JokeApp application = new JokeApp(dataSource, menu);
//	application.run();
	

		
	private final static String ENDPOINT = "http://api.icndb.com/jokes/random?exclude=explicit";
	
	private final static String END = "https://www.dictionaryapi.com/api/v3/references/thesaurus/json/";
	private final static String KEY = "?key=ca9b93dd-8df7-4fd7-bc5a-866b7b5dac4a";
	
	private static RestTemplate restTemplate = new RestTemplate();
	private JdbcTemplate jdbcTemplate;
	
	private static final Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//handled in Application.properties
//		BasicDataSource wordDataSource = new BasicDataSource();
//		wordDataSource.setUrl("jdbc:postgresql://localhost:5432/WordBase");
//		wordDataSource.setUsername("postgres");
//		wordDataSource.setPassword("postgres1");
//		
//		JdbcTemplate wordJdbcTemplate = new JdbcTemplate(wordDataSource);

		
		System.out.println("Enter a word to search for:");
		String newWordToSearch = in.next();
//
//		
		String url = END + newWordToSearch + KEY;
//		String urbanUrl = "http://api.urbandictionary.com/v0/define?term=book" + newWordToSearch;
		
		//restTemplate connected to API to get syns
		//needs an exception handler for words not found
		
		Root[] theWord = restTemplate.getForObject(url, Root[].class);
		
		
		//restTemplate connected to API to get syns
		//needs an exception handler for words not found
		
		//Root urbanWord = restTemplate.getForObject(urbanUrl, Root.class);
		

		//System.out.println(theWord[0].getMeta().getSyns().get(0));
		//System.out.println(theWord[0].getFl());
		System.out.println(theWord[0].getMeta());
		
		//System.out.println(urbanWord);
				
				
		//jdbcTemplate connected to sql server /WordBase
		
		
		
		String newSentence =  "I wonder where; the !business has books :joy:";
		
		//word length of post
		String[] sentenceWordAsArray = newSentence.replaceAll("[^a-zA-Z ]", "").split("\\s+");
		int sentenceWordCount = sentenceWordAsArray.length;
		
		//amount of punctuation--grammar implications
		String[] sentencePunctuationAsArray = newSentence.replaceAll("[a-zA-Z ]", "").split("\\s+");
		int sentencePunctuationCount = sentencePunctuationAsArray.length;
		
		double puncuationPercentage = sentencePunctuationCount / sentenceWordCount;
		
		
		//average word length per post ***************
//		public double getAvgCharacterCount(String userSentence) {
//			String[] sentenceWordAsArray = userSentence.replaceAll("[^a-zA-Z ]", "").split("\\s+");
//			
//			int characterCount = 0;
//			for (String word : sentenceWordAsArray) {
//				characterCount+= word.length();
//			}
//			double avgCharacterCount = characterCount / sentenceWordAsArray.length;
//			return avgCharacterCount;
//		}
		
		
		//average word length over 4 per post *******
//		int characterCountOver3 = 0;
//		for (String word : sentenceWordAsArray) {
//			if (word.length()>4) {
//				characterCountOver3 += word.length();
//			}
//		}
//		double avgCharacterCountOver3 = characterCount / sentenceWordAsArray.length;
//		//return avgCharacterCount;
		
		
		//count of emojis
		int emojiCounter = 0;
		String[] sentenceEmojisArray = newSentence.split("\\s+"); // split on whitespace
		for (String word : sentenceEmojisArray) { 
			//plain emoji w normal spacing
			if (word.matches("^:") && word.matches(":$") && word.length()>4) { 
				int colonCounter = 0;
				for (int i=0; i< word.length(); i++) { //possible emojis side by side w no spaces between
					if (word.charAt(i)==(':')) {
						colonCounter += 1;
					}
				}
				if (colonCounter%2==0) {
					emojiCounter += (colonCounter/2);
				}
				else {
					emojiCounter += ((colonCounter-1)/2); //checks for extra colons at beg or end 
				}
				
				emojiCounter+=1;
			}
			//checks longer words for emojis that make use of colon 
				//(doesn't account for "(emoji)::" or "::(emoji)" --> that would count as 2 emojis
			if (word.length()>4 && word.contains(":")) {
				int colonCounter = 0;
				for (int i=0; i< word.length(); i++) { //possible emojis side by side w no spaces between
					if (word.charAt(i)==(':')) {
						colonCounter += 1;
					}
				}
				if (colonCounter%2==0) {
					emojiCounter += (colonCounter/2);
				}
				else {
					emojiCounter += ((colonCounter-1)/2);
				}
				
				emojiCounter+=1;
			}

		}
		
		
		
		//subject-verb proximity as measure of directness --> 0 to 1
		//either one of the first 2 words is a verb or verb is really close to pronoun
		for (int i = 0; i<sentenceWordAsArray.length && i<8; i++) {
			//findingVerbPosition
			int verbPosition;
			String currentWordUrl = END + sentenceWordAsArray[i] + KEY;
			Root[] currentWord = restTemplate.getForObject(currentWordUrl, Root[].class);
			if(currentWord[0].getFl().equalsIgnoreCase("verb")) {
				verbPosition = i;
				double measureOfDirectness;
				for (int z = verbPosition-1; z>verbPosition-3; z--) {
					if (sentenceWordAsArray[z].equalsIgnoreCase("i") || sentenceWordAsArray[z].equalsIgnoreCase("he") || sentenceWordAsArray[z].equalsIgnoreCase("she")){
						
						measureOfDirectness = 1/(verbPosition-z);
					} else {
						measureOfDirectness = 0;
					}
				}
			}
		//return measureOfDirectness;
		}

		
		//count of 'I's + 'me's
		int iPronounCounter = 0;
		for (String word: sentenceWordAsArray) {
			if (word.equalsIgnoreCase("i") || word.equalsIgnoreCase("me") ) {
				iPronounCounter += 1;
			}
		}
		
		//count of 'we's
		int mePronounCounter = 0;
		for (String word: sentenceWordAsArray) {
			if (word.equalsIgnoreCase("we") ) {
				mePronounCounter += 1;
			}
		}
		
		
		
		//adjective + adverb usage %
		
		
		
		// verb tenses
		
				
		
		

	}

}



