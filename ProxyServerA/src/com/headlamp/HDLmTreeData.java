package com.headlamp;
/**
 * HDLmTreeData short summary.
 *
 * HDLmTreeData description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */
public class HDLmTreeData {
	/* This class can never be instantiated */
	private HDLmTreeData() {}
	/* The JSON below is sent to the bridge to execute an insert
	   statement */
	protected static final String  jsonBridgeInsert = 
		"{ \"data\": " +
		  "[" +
		    "{" +
		    "\"content\": \"pass_javaa\", " +
		    "\"info\": " +
	  	      "{" +
		        "\"tooltip\":\"Data node\"," +
		        "\"type\":\"data\"," +
		        "\"nodePath\":" +
		            "[\"Top\",\"Companies\",\"zyxwvutsrq9876543210.com\",\"Data\"]," +
	  	      "\"details\":" +
	 	            "{" +
		             "\"type\":\"data\"," +
		             "\"name\":\"Data\"," +
		             "\"countDivisions\":0," +
		             "\"updated\":false," +
		             "\"created\":\"2022-11-18T02:04:30.296Z\"," +
		             "\"lastmodified\":\"2022-11-18T02:04:30.296Z\"" +
		            "}" +
		        "}," + 
	 	    "\"name\": \"Data\"" + 
		    "}," +
		    "{" +
		    "\"content\": \"pass_javaa\", " +
		    "\"info\": " +
	  	      "{" +
		        "\"tooltip\":\"Ignore-lists node\"," +
		        "\"type\":\"lists\"," +
		        "\"nodePath\":" +
		            "[\"Top\",\"Companies\",\"zyxwvutsrq9876543210.com\",\"Ignore Lists\"]," +
	    	    "\"details\":" +
	 	            "{" +
		             "\"type\":\"lists\"," +
		             "\"name\":\"Ignore Lists\"," +
		             "\"countLists\":0," +
		             "\"updated\":false," +
		             "\"created\":\"2022-11-18T02:04:30.296Z\"," +
		             "\"lastmodified\":\"2022-11-18T02:04:30.296Z\"" +
		            "}" +
		        "}," + 
	 	    "\"name\": \"Ignore Lists\"" + 
		    "}," +
		    "{" +
		    "\"content\": \"pass_javaa\", " +
		    "\"info\": " +
	  	      "{" +
		        "\"tooltip\":\"Reports node\"," +
		        "\"type\":\"reports\"," +
		        "\"nodePath\":" +
		            "[\"Top\",\"Companies\",\"zyxwvutsrq9876543210.com\",\"Reports\"]," +
	    	    "\"details\":" +
	 	            "{" +
		             "\"type\":\"reports\"," +
		             "\"name\":\"Reports\"," +
		             "\"countReports\":0," +
		             "\"updated\":false," +
		             "\"created\":\"2022-11-18T02:04:30.296Z\"," +
		             "\"lastmodified\":\"2022-11-18T02:04:30.296Z\"" +
		            "}" +
		        "}," + 
	 	    "\"name\": \"Reports\"" + 
		    "}," +
		    "{" +
		    "\"content\": \"pass_javaa\", " +
		    "\"info\": " +
	  	      "{" +
		        "\"tooltip\":\"Rules node\"," +
		        "\"type\":\"rules\"," +
		        "\"nodePath\":" +
		            "[\"Top\",\"Companies\",\"zyxwvutsrq9876543210.com\",\"Rules\"]," +
	    	      "\"details\":" +
	 	            "{" +
		             "\"type\":\"rules\"," +
		             "\"name\":\"Rules\"," +
		             "\"countDivisions\":0," +
		             "\"updated\":false," +
		             "\"created\":\"2022-11-18T02:04:30.296Z\"," +
		             "\"lastmodified\":\"2022-11-18T02:04:30.296Z\"" +
		            "}" +
		        "}," + 
	 	    "\"name\": \"Rules\"" + 
		    "}," +
		    "{" +
		    "\"content\": \"pass_javaa\", " +
		    "\"info\": " +
	  	      "{" +
		        "\"tooltip\":\"Company node\"," +
		        "\"type\":\"company\"," +
		        "\"nodePath\":" +
		            "[\"Top\",\"Companies\",\"zyxwvutsrq9876543210.com\"]," +
	    	      "\"details\":" +
	 	            "{" +
		             "\"type\":\"company\"," +
		             "\"name\":\"zyxwvutsrq9876543210.com\"," +
		             "\"extra\":\"\"," +
		             "\"enabled\":true," +
		             "\"updated\":true," +
		             "\"passThru\":false," +
		             "\"created\":\"2022-11-18T02:04:30.296Z\"," +
		             "\"lastmodified\":\"2022-11-18T02:04:30.296Z\"" +
		            "}" +
		        "}," + 
	 	    "\"name\": \"zyxwvutsrq9876543210.com\"" + 
		    "}" +
		  "]" +
	  "}"; 	  
	/* Define a set of strings that are combined below */
  private static String  c1 = "{\"data\": "; 
	private static String  c2 = "[";
	private static String  c3 =	"{\"info\": {\"type\": \"data\", \"details\": {\"name\": \"Data\", \"type\": \"data\", \"created\": \"2022-11-18T02:04:30.296Z\", \"updated\": false, \"lastmodified\": \"2022-11-18T02:04:30.296Z\", \"countDivisions\": 0}, \"tooltip\": \"Data node\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\", \"Data\"]}, \"content\":\"pass_javaa\",\"name\":\"Data\",\"company\":\"zyxwvutsrq9876543210.com\"},";
	private static String  c4	= "{\"info\": {\"type\": \"lists\", \"details\": {\"name\": \"Ignore Lists\", \"type\": \"lists\", \"created\": \"2022-11-18T02:04:30.296Z\", \"updated\": false, \"countLists\": 0, \"lastmodified\": \"2022-11-18T02:04:30.296Z\"}, \"tooltip\": \"Ignore-lists node\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\", \"Ignore Lists\"]}, \"content\":\"pass_javaa\",\"name\":\"Ignore Lists\",\"company\":\"zyxwvutsrq9876543210.com\"},";
	private static String  c5	= "{\"info\": {\"type\": \"reports\", \"details\": {\"name\": \"Reports\", \"type\": \"reports\", \"created\": \"2022-11-18T02:04:30.296Z\", \"updated\": false, \"countReports\": 0, \"lastmodified\": \"2022-11-18T02:04:30.296Z\"}, \"tooltip\": \"Reports node\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\", \"Reports\"]}, \"content\":\"pass_javaa\",\"name\":\"Reports\",\"company\":\"zyxwvutsrq9876543210.com\"},";
	private static String  c6	= "{\"info\": {\"type\": \"rules\", \"details\": {\"name\": \"Rules\", \"type\": \"rules\", \"created\": \"2022-11-18T02:04:30.296Z\", \"updated\": false, \"lastmodified\": \"2022-11-18T02:04:30.296Z\", \"countDivisions\": 0}, \"tooltip\": \"Rules node\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\", \"Rules\"]}, \"content\":\"pass_javaa\",\"name\":\"Rules\",\"company\":\"zyxwvutsrq9876543210.com\"},";
	private static String  c7	= "{\"info\": {\"type\": \"company\", \"details\": {\"name\": \"zyxwvutsrq9876543210.com\", \"type\": \"company\", \"extra\": \"\", \"created\": \"2022-11-18T02:04:30.296Z\", \"enabled\": true, \"updated\": true, \"passThru\": false, \"lastmodified\": \"2022-11-18T02:04:30.296Z\"}, \"tooltip\": \"Company node\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\"]}, \"content\":\"pass_javaa\",\"name\":\"zyxwvutsrq9876543210.com\",\"company\":\"zyxwvutsrq9876543210.com\"},";
	private static String  c8	= "{\"info\": {\"type\": \"division\", \"tooltip\": \"Division node\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\", \"Rules\", \"example.com\"]}, \"content\":\"pass_javaa\",\"name\":\"example.com\",\"company\":\"zyxwvutsrq9876543210.com\"},";
	private static String  c9	= "{\"info\": {\"type\": \"site\", \"tooltip\": \"Site node\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\", \"Rules\", \"example.com\", \"example.com\"]}, \"content\":\"pass_javaa\",\"name\":\"example.com\",\"company\":\"zyxwvutsrq9876543210.com\"},";
	private static String  d1	= "{\"info\": {\"type\": \"mod\", \"details\": {\"find\": [], \"name\": \"Notify Add To Cart\", \"path\": \"//.*/\", \"type\": \"notify\", \"extra\": \"\", \"xpath\": \"\", \"enabled\": false, \"targets\": [], \"updated\": false, \"comments\": \"\", \"nodeiden\": {\"type\": \"class\", \"counts\": {\"tag\": 102, \"name\": 1, \"class\": 1}, \"parent\": {\"tag\": \"div\", \"class\": [\"product-form__buttons\"], \"innertext\": \"add to cart\"}, \"attributes\": {\"tag\": \"button\", \"name\": \"add\", \"type\": \"submit\", \"class\": [\"product-form__submit\", \"button\", \"button--full-width\", \"button--secondary\"], \"bestclass\": \"product-form__submit\", \"innertext\": \"add to cart\"}, \"grandparent\": {\"tag\": \"form\", \"class\": [\"form\"], \"action\": \"/cart/add\", \"method\": \"post\", \"enctype\": \"multipart/form-data\", \"data-type\": \"add-to-cart-form\", \"innertext\": \"add to cart\", \"novalidate\": \"novalidate\", \"accept-charset\": \"UTF-8\"}}, \"cssselector\": \"\"}, \"tooltip\": \"Notify modification\", \"nodePath\": [\"Top\", \"Companies\", \"zyxwvutsrq9876543210.com\", \"Rules\", \"example.com\", \"example.com\", \"Notify Add To Cart\"]}, \"content\":\"pass_javaa\",\"name\":\"Notify Add To Cart\",\"company\":\"zyxwvutsrq9876543210.com\"}";
	private static String  d2 = "]"; 
	private static String  d3 = "}";
	/* Combine the strings into a single string. This rule is used to test (with Junit5) the 
	   transfer mechanism and the undo/redo mechanism. */
	protected static final String  jsonUnReInsert = c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9 + d1 + d2 + d3;
  /* This is the test data used to test (with Junit5) HDLmTree. This test data is
	   fixed so that the tests yield reproducible results. The test data was obtained
	   from a live system, so that it is quite complex and useful. */
	protected static final String  jsonGetModStr =
		"{" +
			  "\"rows_returned\": 1," +
			  "\"comments\": \"io for test_2\"," +
			  "\"data\": [" +
			    "{" +
			      "\"company\": \"example.com\"," +
			      "\"content\": \"mod_javaa\"," +
			      "\"created\": 1527005834270," +
			      "\"division\": \"example.com\"," +
			      "\"enabled\": true," +
			      "\"id\": \"6784d8d287c9438d89ebb4d389e70877\"," +
			      "\"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
			      "\"meta_classification\": \"\"," +
			      "\"meta_datatag\": \"[]\"," +
			      "\"meta_schema\": \"\"," +
			      "\"mods\": \"[{\\\"tooltip\\\":\\\"Top node of the node tree\\\",\\\"type\\\":\\\"top\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Title modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Add to Cart Text\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"button\\\",\\\"attribute\\\":\\\"id\\\",\\\"value\\\":\\\"product-addtocart-button\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":9,\\\"type\\\":\\\"title\\\",\\\"titles\\\":[\\\"ADD TO CART\\\",\\\"TCELESECROFAdd to ala-carte\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Add to Cart Text\\\"]},{\\\"tooltip\\\":\\\"Font color modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Color\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":1,\\\"type\\\":\\\"fontcolor\\\",\\\"colors\\\":[\\\"#000000\\\",\\\"#0000ff\\\",\\\"#00ff00\\\",\\\"#ff0000\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Color\\\"]},{\\\"tooltip\\\":\\\"Font family modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Family\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":3,\\\"type\\\":\\\"fontfamily\\\",\\\"families\\\":[\\\"arial\\\",\\\"arial\\\",\\\"cursive\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Family\\\"]},{\\\"tooltip\\\":\\\"Font kerning modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Kerning\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":4,\\\"type\\\":\\\"fontkerning\\\",\\\"kernings\\\":[\\\"auto\\\",\\\"TCELESECROFnone\\\",\\\"normal\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Kerning\\\"]},{\\\"tooltip\\\":\\\"Font size modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Size\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"fontsize\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"parameter\\\":0,\\\"updated\\\":false,\\\"sizes\\\":[\\\"100px\\\",\\\"TCELESECROF20px\\\",\\\"30\\\",\\\"40px\\\",\\\"50px\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Size\\\"]},{\\\"tooltip\\\":\\\"Font style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Style\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":5,\\\"type\\\":\\\"fontstyle\\\",\\\"styles\\\":[\\\"italic\\\",\\\"normal\\\",\\\"TCELESECROFoblique\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Style\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Text\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"TCELESECROFVery Cuter\\\",\\\"Very Cute\\\",\\\"Not so cute\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div[1]/main/div[2]/div[1]/div[1]/div[1]/h1/span\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Text\\\"]},{\\\"tooltip\\\":\\\"Font weight modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Weight\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":6,\\\"type\\\":\\\"fontweight\\\",\\\"weights\\\":[\\\"normal\\\",\\\"bold\\\",\\\"lighter\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Weight\\\"]},{\\\"tooltip\\\":\\\"Height modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Logo Height\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"height\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[{\\\"tag\\\":\\\"a\\\",\\\"attribute\\\":\\\"class\\\",\\\"value\\\":\\\"logo\\\"},{\\\"tag\\\":\\\"img\\\",\\\"attribute\\\":\\\"\\\",\\\"value\\\":\\\"\\\"}],\\\"parameter\\\":8,\\\"updated\\\":false,\\\"heights\\\":[\\\"145px\\\",\\\"45px\\\",\\\"50%\\\",\\\"TCELESECROFauto\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Logo Height\\\"]},{\\\"tooltip\\\":\\\"Width modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Logo Width\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"width\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[{\\\"tag\\\":\\\"a\\\",\\\"attribute\\\":\\\"class\\\",\\\"value\\\":\\\"logo\\\"},{\\\"tag\\\":\\\"img\\\",\\\"attribute\\\":\\\"\\\",\\\"value\\\":\\\"\\\"}],\\\"parameter\\\":7,\\\"updated\\\":true,\\\"widths\\\":[\\\"TCELESECROF250px\\\",\\\"450px\\\",\\\"auto\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Logo Width\\\"]},{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Parameters\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"form\\\",\\\"attribute\\\":\\\"id\\\",\\\"value\\\":\\\"product_addtocart_form\\\"}],\\\"enabled\\\":true,\\\"targets\\\":[],\\\"type\\\":\\\"notify\\\",\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Parameters\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Change 7.62\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"7.62\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"$7.00\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change 7.62\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Order modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Change Order\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#ticket-package > div > div\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"order\\\",\\\"parameter\\\":3,\\\"updated\\\":true,\\\"orders\\\":[\\\"0,3,2,1,4,5,6\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Change Order\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Hero\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"background\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"styles\\\":[\\\"#014f90 url(https://res.cloudinary.com/legends-production/image/upload/v1547115695/owo-prod/assets/ticket-package-combination.jpg) no-repeat\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Hero\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Hero Size\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"background-size\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"styles\\\":[\\\"cover\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Hero Size\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Text\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"Buy\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":true,\\\"newtexts\\\":[\\\"Visit Options\\\",\\\"Ticket Options\\\",\\\"Buy Options\\\",\\\"Purchase Now\\\",\\\"Reserve Now\\\",\\\"Buy Now\\\",\\\"Purchase Tickets\\\",\\\"Reserve Tickets\\\",\\\"Buy Tickets\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Text\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Change Experience\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"NYC Inspired Cuisine\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"NYC Cusine for Oscar and Mike\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Experience\\\"]},{\\\"tooltip\\\":\\\"Attribute modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Fix Href\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"href/useproxyhost\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[3]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"attribute\\\",\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Href\\\"]},{\\\"tooltip\\\":\\\"Modify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Fix Iframe\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"fixiframesrc\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#override\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"modify\\\",\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Iframe\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Free Cheesecake\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#id-116 + span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"text\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Free Cheesecake\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Free Cheesecake\\\"]},{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Parameters\\\",\\\"path\\\":\\\"/\\\",\\\"enabled\\\":true,\\\"targets\\\":[],\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"notify\\\",\\\"parameter\\\":null,\\\"updated\\\":false,\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Parameters\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Style Color\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"background-image\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"styles\\\":[\\\"linear-gradient(to left,#ffff00,#0000ff)\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Style Color\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Style Width\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"width\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"styles\\\":[\\\"250px\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Style Width\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Text\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"Buy Tickets\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Visit Options\\\",\\\"Ticket Options\\\",\\\"Buy Options\\\",\\\"Purchase Now\\\",\\\"Reserve Now\\\",\\\"Buy Now\\\",\\\"Puchase Tickets\\\",\\\"Reserve Tickets\\\",\\\"Buy Tickets\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Text\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Top Line Experience\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy From Chris\\\",\\\"Buy From Mr. Tanner\\\",\\\"Buy From Julian\\\",\\\"Buy Now!!!\\\",\\\"Top OWO Experience Now\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Top Line Experience\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Top Line Tickets\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"Buy Tickets\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Visit Options\\\",\\\"Ticket Options\\\",\\\"Buy Options\\\",\\\"Purchase Now\\\",\\\"Reserve Now\\\",\\\"Buy Now\\\",\\\"Puchase Tickets\\\",\\\"Reserve Tickets\\\",\\\"Buy Tickets\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Top Line Tickets\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Remove Mastercard\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"Mastercard\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"A Guided Experience for Almost All\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Remove Mastercard\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Change 7.62\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"7.62\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"$7.00\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change 7.62\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Bottom Parameters\\\",\\\"path\\\":\\\"/\\\",\\\"enabled\\\":false,\\\"targets\\\":[],\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"notify\\\",\\\"parameter\\\":null,\\\"updated\\\":false,\\\"extra\\\":\\\"Extra Bott\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Bottom Parameters\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Bottom Text\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Now!!!\\\",\\\"Buy Tickets Cheapest\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Bottom Text\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Change Experience\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"NYC Inspired Cuisine\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"NYC Cusine for Oscar and Mike\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Experience\\\"]},{\\\"tooltip\\\":\\\"Order modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Change Order\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#ticket-package > div > div\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"order\\\",\\\"parameter\\\":3,\\\"updated\\\":false,\\\"orders\\\":[\\\"0,3,2,1,4,5,6\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Order\\\"]},{\\\"tooltip\\\":\\\"Attribute modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Fix Href\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"href/useproxyhost\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"attribute\\\",\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Href\\\"]},{\\\"tooltip\\\":\\\"Modify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Fix Iframe\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"fixiframesrc\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#override\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"modify\\\",\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Iframe\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Free Cheesecake\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#id-116 + span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"text\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Free Cheesecake\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Free Cheesecake\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Remove Mastercard\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"Mastercard\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"A Guided Experience for Almost All\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Remove Mastercard\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Top Line Experience\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy From Chris\\\",\\\"Buy From Mr. Tanner\\\",\\\"Buy From Julian\\\",\\\"Buy Now!!!\\\",\\\"Top OWO Experience Now\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Top Line Experience\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Top Line Tickets\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Now!!!\\\",\\\"Top OWO Tickets\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Top Line Tickets\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets $35 Text\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"text\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[3]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Buy Tickets\\\",\\\"Reserve Tickets\\\",\\\"Purchase Tickets\\\",\\\"Buy Now\\\",\\\"Reserve Now\\\",\\\"Purchase Now\\\",\\\"Buy Options\\\",\\\"Ticket Options\\\",\\\"Visit Options\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets $35 Text\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets $45 Text\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"text\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[2]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Buy Tickets\\\",\\\"Reserve Tickets\\\",\\\"Purchase Tickets\\\",\\\"Buy Now\\\",\\\"Reserve Now\\\",\\\"Purchase Now\\\",\\\"Buy Options\\\",\\\"Ticket Options\\\",\\\"Visit Options\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets $45 Text\\\"]},{\\\"tooltip\\\":\\\"Attribute modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets $55 Fix Href\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"href/useproxyhost\\\",\\\"enabled\\\":false,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"attribute\\\",\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets $55 Fix Href\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Change Experience\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"NYC Inspired Cuisine\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"NYC Cusine for Oscar and Mike\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Change Experience\\\"]},{\\\"tooltip\\\":\\\"Order modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Change Order\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#ticket-package > div > div\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"order\\\",\\\"parameter\\\":3,\\\"updated\\\":false,\\\"orders\\\":[\\\"0,3,2,1,4,5,6\\\",\\\"0,3,2,1,4,5,6\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Change Order\\\"]},{\\\"tooltip\\\":\\\"Modify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Fix Iframe\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"fixiframesrc\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#override\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"modify\\\",\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Fix Iframe\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Free Cheesecake\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#id-116 + span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"text\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Free Cheesecake\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Free Cheesecake\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Buy Tickets Remove Mastercard\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"Mastercard\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"A Guided Experience\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Remove Mastercard\\\"]},{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Parameters\\\",\\\"path\\\":\\\"/\\\",\\\"enabled\\\":true,\\\"targets\\\":[],\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"notify\\\",\\\"parameter\\\":null,\\\"updated\\\":false,\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Parameters\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Style Color\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"background-image\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"styles\\\":[\\\"linear-gradient(to left,#f05b83,#003f6c\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Style Color\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Style Width\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"width\\\",\\\"enabled\\\":true,\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"updated\\\":false,\\\"styles\\\":[\\\"100px\\\",\\\"150px\\\",\\\"200px\\\",\\\"250px\\\",\\\"300px\\\",\\\"350px\\\",\\\"400px\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Style Width\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Bottom Text\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Tickets\\\",\\\"Reserve Tickets\\\",\\\"Purchase Tickets\\\",\\\"Buy Now\\\",\\\"Reserve Now\\\",\\\"Purchase Now\\\",\\\"Buy Options\\\",\\\"Ticket Options\\\",\\\"Visit Options\\\"],\\\"updated\\\":true,\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Text\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Top Line Experience\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Experience\\\",\\\"Experience\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Top Line Experience\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"OWO Home Top Line Tickets\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Tickets\\\",\\\"Reserve Tickets\\\",\\\"Purchase Tickets\\\",\\\"Buy Now\\\",\\\"Reserve Now\\\",\\\"Purchase Now\\\",\\\"Buy Options\\\",\\\"Ticket Options\\\",\\\"Visit Options\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\",\\\"nodeiden\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Top Line Tickets\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\"]}]\"," +
			      "\"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
			      "\"relationships\": \"\"," +
			      "\"site\": \"example.com\"," +
			      "\"updated\": 1561696711267" +
			    "}" +
			  "]," +
			  "\"retcode\": 1" +
		"}";
	/* This is the (saved) test data used to test (with Junit5) HDLmTree. This
	   test data is fixed so that the tests yield reproducible results. The test
	   data was obtained from a live system, so that it is quite complex and useful. */
	protected static final String  jsonGetModStrSaved2 =
	  "{" +
		   "\"rows_returned\": 1," +
		   "\"comments\": \"io for test_2\"," +
		   "\"data\": [" +
		     "{" +
		       "\"company\": \"example.com\"," +
		       "\"content\": \"mod_javac\"," +
		       "\"created\": 1527005834270," +
		       "\"division\": \"example.com\"," +
		       "\"enabled\": true," +
		       "\"id\": \"6784d8d287c9438d89ebb4d389e70877\"," +
		       "\"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
		       "\"meta_classification\": \"\"," +
		       "\"meta_datatag\": \"[]\"," +
		       "\"meta_schema\": \"\"," +
		       "\"mods\":" +
		       " \"" +
		       "[{\\\"tooltip\\\":\\\"Top node of the node tree\\\",\\\"type\\\":\\\"top\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Title modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Add to Cart Text\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"button\\\",\\\"attribute\\\":\\\"id\\\",\\\"value\\\":\\\"product-addtocart-button\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":9,\\\"type\\\":\\\"title\\\",\\\"titles\\\":[\\\"ADD TO CART\\\",\\\"Add to ala-carte\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Add to Cart Text\\\"]},{\\\"tooltip\\\":\\\"Font color modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"prob\\\":100.0,\\\"usemode\\\":\\\"prod\\\",\\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\":\\\"Font Color\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":1,\\\"type\\\":\\\"fontcolor\\\",\\\"colors\\\":[\\\"#000000\\\",\\\"#0000ff\\\",\\\"#00ff00\\\",\\\"#ff0000\\\",\\\"#ff0000\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Color\\\"]},{\\\"tooltip\\\":\\\"Font family modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Font Family\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":3,\\\"type\\\":\\\"fontfamily\\\",\\\"families\\\":[\\\"arial\\\",\\\"arial\\\",\\\"cursive\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Family\\\"]},{\\\"tooltip\\\":\\\"Font kerning modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Font Kerning\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":4,\\\"type\\\":\\\"fontkerning\\\",\\\"kernings\\\":[\\\"auto\\\",\\\"none\\\",\\\"normal\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Kerning\\\"]},{\\\"tooltip\\\":\\\"Font size modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Font Size\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":0,\\\"type\\\":\\\"fontsize\\\",\\\"sizebase\\\":70,\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Size\\\"]},{\\\"tooltip\\\":\\\"Font style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Font Style\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":5,\\\"type\\\":\\\"fontstyle\\\",\\\"styles\\\":[\\\"normal\\\",\\\"italic\\\",\\\"oblique\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Style\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Font Text\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Not so cute\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div[1]/main/div[2]/div[1]/div[1]/div[1]/h1/span\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Text\\\"]},{\\\"tooltip\\\":\\\"Font weight modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Font Weight\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"span\\\",\\\"attribute\\\":\\\"itemprop\\\",\\\"value\\\":\\\"name\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":6,\\\"type\\\":\\\"fontweight\\\",\\\"weights\\\":[\\\"normal\\\",\\\"bold\\\",\\\"lighter\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Font Weight\\\"]},{\\\"tooltip\\\":\\\"Height modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Logo Height\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"a\\\",\\\"attribute\\\":\\\"class\\\",\\\"value\\\":\\\"logo\\\"},{\\\"tag\\\":\\\"img\\\",\\\"attribute\\\":\\\"\\\",\\\"value\\\":\\\"\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":8,\\\"type\\\":\\\"height\\\",\\\"heightbase\\\":\\\"auto\\\",\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Logo Height\\\"]},{\\\"tooltip\\\":\\\"Width modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Logo Width\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"a\\\",\\\"attribute\\\":\\\"class\\\",\\\"value\\\":\\\"logo\\\"},{\\\"tag\\\":\\\"img\\\",\\\"attribute\\\":\\\"\\\",\\\"value\\\":\\\"\\\"}],\\\"enabled\\\":true,\\\"parameter\\\":7,\\\"type\\\":\\\"width\\\",\\\"widthbase\\\":\\\"450px\\\",\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Logo Width\\\"]},{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"Parameters\\\",\\\"path\\\":\\\"/neve-studio-dance-jacket.html\\\",\\\"find\\\":[{\\\"tag\\\":\\\"form\\\",\\\"attribute\\\":\\\"id\\\",\\\"value\\\":\\\"product_addtocart_form\\\"}],\\\"enabled\\\":true,\\\"targets\\\":[],\\\"targets\\\":[],\\\"type\\\":\\\"notify\\\",\\\"updated\\\":false,\\\"xpath\\\":\\\"\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"Parameters\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-magento.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Change 7.62\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"7.62\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"$7.00\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change 7.62\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo-secure.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Order modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Buy Tickets Change Order\\\",\\\"path\\\":\\\"/en-US/buy-ticket\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#ticket-package > div > div\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"order\\\",\\\"parameter\\\":3,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"orders\\\":[\\\"0,3,2,1,4,5,6\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Change Order\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Buy Tickets Hero\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"background\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"styles\\\":[\\\"#014f90 url(https://res.cloudinary.com/legends-production/image/upload/v1547115695/owo-prod/assets/ticket-package-combination.jpg) no-repeat\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Hero\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Buy Tickets Hero Size\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"background-size\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"styles\\\":[\\\"cover\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Hero Size\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Buy Tickets Text\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"Buy\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Visit Options\\\",\\\"Ticket Options\\\",\\\"Buy Options\\\",\\\"Purchase Now\\\",\\\"Reserve Now\\\",\\\"Buy Now\\\",\\\"Purchase Tickets\\\",\\\"Reserve Tickets\\\",\\\"Buy Tickets\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Buy Tickets Text\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Change Experience\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"NYC Inspired Cuisine\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"NYC Cusine for Oscar and Mike\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Experience\\\"]},{\\\"tooltip\\\":\\\"Attribute modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Fix Href\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"href/useproxyhost\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[3]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"attribute\\\",\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Href\\\"]},{\\\"tooltip\\\":\\\"Modify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Fix Iframe\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"fixiframesrc\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#override\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"modify\\\",\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Iframe\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Free Cheesecake\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#id-116 + span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"text\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Free Cheesecake\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Free Cheesecake\\\"]},{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Home Bottom Parameters\\\",\\\"path\\\":\\\"/\\\",\\\"enabled\\\":true,\\\"targets\\\":[],\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"notify\\\",\\\"parameter\\\":null,\\\"value\\\":null,\\\"values\\\":[],\\\"valueSuffix\\\":\\\"\\\",\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Parameters\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Home Bottom Style Color\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"background-image\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"styles\\\":[\\\"linear-gradient(to left,#ffff00,#0000ff)\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Style Color\\\"]},{\\\"tooltip\\\":\\\"Style modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Home Bottom Style Width\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"width\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"style\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":true,\\\"styles\\\":[\\\"250px\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Style Width\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Home Bottom Text\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"Buy Tickets\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Visit Options\\\",\\\"Ticket Options\\\",\\\"Buy Options\\\",\\\"Purchase Now\\\",\\\"Reserve Now\\\",\\\"Buy Now\\\",\\\"Puchase Tickets\\\",\\\"Reserve Tickets\\\",\\\"Buy Tickets\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Bottom Text\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Home Top Line Experience\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy From Chris\\\",\\\"Buy From Mr. Tanner\\\",\\\"Buy From Julian\\\",\\\"Buy Now!!!\\\",\\\"Top OWO Experience Now\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Top Line Experience\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Home Top Line Tickets\\\",\\\"path\\\":\\\"/\\\",\\\"extra\\\":\\\"Buy Tickets\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Visit Options\\\",\\\"Ticket Options\\\",\\\"Buy Options\\\",\\\"Purchase Now\\\",\\\"Reserve Now\\\",\\\"Buy Now\\\",\\\"Puchase Tickets\\\",\\\"Reserve Tickets\\\",\\\"Buy Tickets\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Home Top Line Tickets\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Remove Mastercard\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"Mastercard\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"A Guided Experience for Almost All\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Remove Mastercard\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Change 7.62\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"7.62\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"$7.00\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change 7.62\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test-secure.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Bottom Parameters\\\",\\\"path\\\":\\\"/\\\",\\\"enabled\\\":false,\\\"targets\\\":[],\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"notify\\\",\\\"parameter\\\":null,\\\"value\\\":null,\\\"values\\\":[],\\\"valueSuffix\\\":\\\"\\\",\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"extra\\\":\\\"Extra Bott\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Bottom Parameters\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Bottom Text\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Now!!!\\\",\\\"Buy Tickets Cheapest\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Bottom Text\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Change Experience\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"NYC Inspired Cuisine\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"NYC Cusine for Oscar and Mike\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Experience\\\"]},{\\\"tooltip\\\":\\\"Order modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Change Order\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#ticket-package > div > div\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"order\\\",\\\"parameter\\\":3,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"orders\\\":[\\\"0,3,2,1,4,5,6\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Order\\\"]},{\\\"tooltip\\\":\\\"Attribute modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Fix Href\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"href/useproxyhost\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"attribute\\\",\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Href\\\"]},{\\\"tooltip\\\":\\\"Modify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Fix Iframe\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"fixiframesrc\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#override\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"modify\\\",\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Iframe\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Free Cheesecake\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#id-116 + span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"text\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Free Cheesecake\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Free Cheesecake\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Remove Mastercard\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"Mastercard\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"A Guided Experience for Almost All\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Remove Mastercard\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Top Line Experience\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy From Chris\\\",\\\"Buy From Mr. Tanner\\\",\\\"Buy From Julian\\\",\\\"Buy Now!!!\\\",\\\"Top OWO Experience Now\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Top Line Experience\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Top Line Tickets\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Now!!!\\\",\\\"Top OWO Tickets\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Top Line Tickets\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-test.dnsalias.com\\\"]},{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Division node\\\",\\\"type\\\":\\\"division\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Site node\\\",\\\"type\\\":\\\"site\\\",\\\"children\\\":[{\\\"tooltip\\\":\\\"Notify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Bottom Parameters\\\",\\\"path\\\":\\\"/\\\",\\\"enabled\\\":true,\\\"targets\\\":[],\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"notify\\\",\\\"parameter\\\":null,\\\"value\\\":null,\\\"values\\\":[],\\\"valueSuffix\\\":\\\"\\\",\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"extra\\\":\\\"Extra Bott\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Bottom Parameters\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Bottom Text\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":true,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Now!!!\\\",\\\"Buy Tickets Cheapest\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Bottom Text\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Change Experience\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"NYC Inspired Cuisine\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"NYC Cusine for Oscar and Mike\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Experience\\\"]},{\\\"tooltip\\\":\\\"Order modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Change Order\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#ticket-package > div > div\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"order\\\",\\\"parameter\\\":3,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"orders\\\":[\\\"0,3,2,1,4,5,6\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Change Order\\\"]},{\\\"tooltip\\\":\\\"Attribute modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Fix Href\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"href/useproxyhost\\\",\\\"enabled\\\":true,\\\"cssselector\\\":\\\"\\\",\\\"xpath\\\":\\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\",\\\"find\\\":[],\\\"type\\\":\\\"attribute\\\",\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Href\\\"]},{\\\"tooltip\\\":\\\"Modify modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Fix Iframe\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"fixiframesrc\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#override\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"modify\\\",\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Fix Iframe\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Free Cheesecake\\\",\\\"path\\\":\\\"/en-US/buy-tickets\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"#id-116 + span\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"text\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"Free Cheesecake\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Free Cheesecake\\\"]},{\\\"tooltip\\\":\\\"Checked type modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Remove Mastercard\\\",\\\"path\\\":\\\"//.*/\\\",\\\"extra\\\":\\\"Mastercard\\\",\\\"enabled\\\":false,\\\"cssselector\\\":\\\"h3.s-ticket-package-description__name\\\",\\\"xpath\\\":\\\"\\\",\\\"find\\\":[],\\\"type\\\":\\\"textchecked\\\",\\\"parameter\\\":2,\\\"value\\\":null,\\\"valueSuffix\\\":\\\"\\\",\\\"values\\\":[],\\\"valuesCount\\\":0,\\\"updated\\\":false,\\\"newtexts\\\":[\\\"A Guided Experience for Almost All\\\"]},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Remove Mastercard\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Top Line Experience\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy From Chris\\\",\\\"Buy From Mr. Tanner\\\",\\\"Buy From Julian\\\",\\\"Buy Now!!!\\\",\\\"Top OWO Experience Now\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Top Line Experience\\\"]},{\\\"tooltip\\\":\\\"Text modification\\\",\\\"type\\\":\\\"mod\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"OWO Top Line Tickets\\\",\\\"path\\\":\\\"/\\\",\\\"find\\\":[],\\\"enabled\\\":false,\\\"parameter\\\":2,\\\"type\\\":\\\"text\\\",\\\"newtexts\\\":[\\\"Buy Now!!!\\\",\\\"Top OWO Tickets\\\"],\\\"updated\\\":false,\\\"xpath\\\":\\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\",\\\"cssselector\\\":\\\"\\\",\\\"extra\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\",\\\"OWO Top Line Tickets\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\",\\\"example.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\"]}]" +
		       "\"," +
		       "\"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
		       "\"relationships\": \"\"," +
		       "\"site\": \"example.com\"," +
		       "\"updated\": 1560172793506" +
		     "}" +
		   "]," +
		   "\"retcode\": 1" +
		 "}";
  /* This is the first part of the test data used to test (with Junit5) HDLmTree */
  protected static String   jsonGetPassStrPart1 =
  		"{" +
  				"    \"rows_returned\": 199," +
  				"    \"comments\": \"io for main_2\"," +
  				"    \"data\": [" +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007053," +
  				"            \"id\": \"580529d5ed4d4133a08f2bb7e4df70c1\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"bskinz.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007053" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007094," +
  				"            \"id\": \"5926ab4f5313487ebbfe0e35845b4cff\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"type\\\": \\\"company\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"oneworldobservatory.com\\\",    \\\"created\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"passThru\\\": false,    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007094" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007127," +
  				"            \"id\": \"df3e9e0b9c904d05baed8d12ab2dd12a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"type\\\": \\\"lists\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"countLists\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007127" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007263," +
  				"            \"id\": \"fe46156ca6cd43d5bc92bcdb3bd55b37\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007263" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007198," +
  				"            \"id\": \"395e37a6615942aab7565ad29163da6d\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"type\\\": \\\"rules\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007198" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007231," +
  				"            \"id\": \"5147d9e1fa414206948d6b75d06ed41b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007231" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007322," +
  				"            \"id\": \"1e2509b6faa94b3396732c92eba782e9\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Main or Top\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slide-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 65,        \\\"class\\\": 1      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-slide-call-to-action\\\"        ],        \\\"tag\\\": \\\"div\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Ticket Options\\\",      \\\"Ticket Buying Options\\\",      \\\"Ticket Package Options\\\",      \\\"Ticket Purchase Options\\\",      \\\"Witness This View\\\",      \\\"Visit Now\\\",      \\\"Reserve Your Experience\\\",      \\\"Join Us on Top of the World\\\",      \\\"Experience the Wonder\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Experience It Now\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Main or Top\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007322" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007468," +
  				"            \"id\": \"64094f1513b8422ab168365b1f09455a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"type\\\": \\\"company\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"owo.dnsalias.com\\\",    \\\"created\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"passThru\\\": false,    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007468" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007614," +
  				"            \"id\": \"9bc89cc1fce149f3a26ed14bc50cffa3\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007614" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007373," +
  				"            \"id\": \"688fb7a6f2a1488abb6cb0e36b3c3552\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Mastercard Holders\\\",    \\\"extra\\\": \\\"Reserve a VIP Tour\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3205\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 65,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"Reserve a VIP Tour\\\",      \\\"Visit As a VIP\\\",      \\\"Take Advantage of This Offer\\\",      \\\"Buy an Exclusive Experience\\\",      \\\"Mastercard Tickets\\\",      \\\"Reserve More Than Just the View\\\",      \\\"Tour As a VIP\\\",      \\\"Buy a VIP Experience\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Mastercard Holders\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007373" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007499," +
  				"            \"id\": \"6f5b314f141d4110a81e7bb469f87d8d\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"type\\\": \\\"lists\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"countLists\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007499" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007637," +
  				"            \"id\": \"05c5392aeee544a8baec202eb8074ed9\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007637" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007411," +
  				"            \"id\": \"e9ff59a560c54d5191b8b915e7b984e9\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Priority Lane\\\",    \\\"extra\\\": \\\"Combination Tickets\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 61,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"Combination Tickets\\\",      \\\"More Than Just the View\\\",      \\\"Combo Tickets\\\",      \\\"Buy a Combination Ticket\\\",      \\\"Purchase a Combination Ticket\\\",      \\\"Buy an Upgraded Experience\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Priority Lane\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007411" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007526," +
  				"            \"id\": \"31a6c89fd2d24e54b1829acc81c0d04c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"type\\\": \\\"reports\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"countReports\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007526" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007682," +
  				"            \"id\": \"faaa436dfadb421380d401790a6bebf2\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Order modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Change Order\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"order\\\",    \\\"path\\\": \\\"/en-US/*\\\",    \\\"cssselector\\\": \\\"#ticket-package > div > div\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"orders\\\": [      \\\"TCELESECROF0,1,2,3,4,5,6\\\",      \\\"0,3,2,1,4,5,6\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Change Order\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007682" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007763," +
  				"            \"id\": \"5eaa3b9d68554a66a6f10a4d3633963d\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Modify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled to enable the iFrame to work\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Fix Iframe\\\",    \\\"extra\\\": \\\"fixiframesrc\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"modify\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"#override\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Fix Iframe\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007763" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007896," +
  				"            \"id\": \"1de072b0c0704bbd867712f95dbcfa96\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image All\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header__container\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"$58\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 133,        \\\"class\\\": 4      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$58\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"TCELESECROF//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Combination.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/20201027+141027_EJ_dusk_OWO_pano_0086-Edit.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/20201027+141027_EJ_dusk_OWO_pano_0058-Edit.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/All+Inclusive.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/k7kLhX9w.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007896" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008049," +
  				"            \"id\": \"997ed012b7084db797394a2c7cd6609c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Stan\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$38\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 5,        \\\"class\\\": 5      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--column\\\",          \\\"s-ticket-package__card--standard\\\",          \\\"js-ticket-package__card\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"$38\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/dsc_5191_retouch.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/q5cJijUw.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/General+Admission.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/20201027+141027_EJ_dusk_OWO_pano_0087-Edit.jpg\\\",      \\\"TCELESECROF//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/joshbarron.jpg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Stan\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008049" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008177," +
  				"            \"id\": \"472be35e80c34ffe81ee1fd8d3486833\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text Stan\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3362\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text Stan\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008177" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008323," +
  				"            \"id\": \"55944f8aefcc4e57a4a54b5c3cf3eb14\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Priority Lane Combination Tickets\\\",    \\\"extra\\\": \\\"Reserve Combination\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3363\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"reserve combination\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 61,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\",        \\\"innertext\\\": \\\"reserve combination\\\"      }    },    \\\"parameter\\\": 13,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Combination Tickets\\\",      \\\"Buy an Upgraded Experience\\\",      \\\"Purchase a Combination Ticket\\\",      \\\"Buy a Combination Ticket\\\",      \\\"Combo Tickets\\\",      \\\"More Than Just the View\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Priority Lane Combination Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008323" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007791," +
  				"            \"id\": \"3e9d5f0b51d54db8b1e1f7006bb40d75\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient All\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"data-accesso-package\\\": \\\"3145\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"TCELESECROFlinear-gradient(to left,#fbd6ac,#f98404)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007791" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007927," +
  				"            \"id\": \"933b5f8fae754e3f89f2c9136b67f2bd\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Combo\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$48\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 5,        \\\"class\\\": 5      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--column\\\",          \\\"s-ticket-package__card--combination\\\",          \\\"js-ticket-package__card\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"$48\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"TCELESECROF//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/aolson__.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Combination.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Combination.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/2Lye8-mA.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/bkawahara.jpg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007927" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008081," +
  				"            \"id\": \"213fb691fbac434fa5ff3cdc2fe8e0da\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Notify Purchase All\\\",    \\\"extra\\\": \\\"Buy All\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\"{\\\\\\\"type\\\\\\\":\\\\\\\"class\\\\\\\",\\\\\\\"attributes\\\\\\\":{\\\\\\\"href\\\\\\\":\\\\\\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\\\\\",\\\\\\\"class\\\\\\\":[\\\\\\\"b-call-to-action__link\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"a\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"},\\\\\\\"counts\\\\\\\":{\\\\\\\"tag\\\\\\\":55,\\\\\\\"class\\\\\\\":6},\\\\\\\"parent\\\\\\\":{\\\\\\\"class\\\\\\\":[\\\\\\\"b-link\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"div\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"}}\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Notify Purchase All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008081" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008209," +
  				"            \"id\": \"69cc8d3df1ca477cacfd60d93bd3971c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Top Line Change Text\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-link\\\",          \\\"is-active\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 18      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Buy Tickets Now\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Top Line Change Text\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008209" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007827," +
  				"            \"id\": \"1cadd9d0617d40c8978c92c8a9b81047\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient Combo\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"TCELESECROFlinear-gradient(to left,#f4a5b9,#f60441)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007827" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007954," +
  				"            \"id\": \"cb368b8e439b46b5ad59f775d58a2904\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Learn More Group\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"m-promo__image\\\",          \\\"c-image-cloudinary\\\",          \\\"c-image-cloudinary--crop-fit\\\",          \\\"c-image-cloudinary--as-background\\\"        ],        \\\"style\\\": \\\"background-image: url(\\\\\\\"https://legends-cloudinary.corebine.com/legends-production/image/upload/c_fit,dpr_2.0,f_webp,g_center,q_auto,w_3840/v1/owo-prod/141027_EJ_dusk_OWO_pano_0079-Edit-2\\\\\\\");\\\",        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\",        \\\"phash\\\": \\\"ccc644cc94b2298a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 99,        \\\"class\\\": 1      },      \\\"parent\\\": {        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\"      }    },    \\\"parameter\\\": 4,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/141027_EJ_dusk_OWO_pano_0079-Edit-2.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/C3bEqZ3Q.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/qHerdAHi.jpg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Learn More Group\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007954" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008112," +
  				"            \"id\": \"e913514182944ba3824a02b60a48e17c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text All\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3364\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008112" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280006729," +
  				"            \"id\": \"2048461e6b514a5ca54cf58c1884d59a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"type\\\": \\\"company\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"bskinz.com\\\",    \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"passThru\\\": false,    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"bskinz.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280006729" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280006990," +
  				"            \"id\": \"eb12c0ee2f4f4ef98856bbf6ae33263d\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"bskinz.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280006990" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007159," +
  				"            \"id\": \"37ddc5a33ccf45d692f6d2f55aefca80\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"type\\\": \\\"reports\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"countReports\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:20:43.166Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007159" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007296," +
  				"            \"id\": \"8561f86a771d46ffbed02fbb859494c2\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience City Pulse\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 65,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Buy a Lesson in History\\\",      \\\"Experience Our Experts\\\",      \\\"Buy the Experience\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience City Pulse\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007296" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007435," +
  				"            \"id\": \"ceecdb6527eb4e8f9628ad82de5ddbb7\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Welcome\\\",    \\\"extra\\\": \\\"All-Inclusive Tickets\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 61,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"All-Inclusive Tickets\\\",      \\\"All-Inclusive Visit\\\",      \\\"Purchase the Ultimate Experience\\\",      \\\"Buy All-Inclusive Tickets\\\",      \\\"Experience All We Have to Offer\\\",      \\\"Experience Everything\\\",      \\\"All-Inclusive Packages\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Welcome\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007435" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007590," +
  				"            \"id\": \"70ce81f67d504b9aabdf41d96b06545b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"type\\\": \\\"rules\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:44:03.848Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007590" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007730," +
  				"            \"id\": \"166c0514b74a499abf3c0ff3fe769761\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Attribute modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Fix Href All\\\",    \\\"extra\\\": \\\"href/useproxyhost\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"attribute\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3364\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Fix Href All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007730" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007862," +
  				"            \"id\": \"ea92b59ed50e476eb225abd1d89f1b02\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient Stan\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2447\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"TCELESECROFlinear-gradient(to left,#b8cdf7,#01cbf2)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient Stan\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007862" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280007989," +
  				"            \"id\": \"e94a0124e2204d6fab83544a49b536df\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Siteseeing\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 5,        \\\"class\\\": 5      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--row\\\",          \\\"s-ticket-package__card--pass\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"the sightseeing pass\\\"      }    },    \\\"parameter\\\": 5,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/vE6ggkZB.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/nAT0n5jy.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Siteseeing\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280007989" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008143," +
  				"            \"id\": \"3b15a6c501a54a24bcd0bace8a8e445b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text Combo\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3363\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008143" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008287," +
  				"            \"id\": \"6669dfb884f245ae9ffcb49b7a4fdbac\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Mastercard Reserve VIP\\\",    \\\"extra\\\": \\\"Reserve a VIP Tour\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3369\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"reserve a vip tour\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 61,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\",        \\\"innertext\\\": \\\"reserve a vip tour\\\"      }    },    \\\"parameter\\\": 12,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Reserve a VIP Tour\\\",      \\\"Visit As a VIP\\\",      \\\"Buy a VIP Experience\\\",      \\\"Tour As a VIP\\\",      \\\"Reserve More Than Just the View\\\",      \\\"Mastercard Tickets\\\",      \\\"Buy an Exclusive Experience\\\",      \\\"Take Advantage of This Offer\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Mastercard Reserve VIP\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008287" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008241," +
  				"            \"id\": \"0dd9301c954d444680ca0a16d70701b7\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Image Priority Lane iPad\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"m-promo__image\\\",          \\\"c-image-cloudinary\\\",          \\\"c-image-cloudinary--crop-fill\\\",          \\\"c-image-cloudinary--as-background\\\"        ],        \\\"style\\\": \\\"background-image: url(\\\\\\\"https://legends-cloudinary.corebine.com/legends-production/image/upload/c_fill,dpr_2.0,f_webp,g_auto,q_auto,w_1170/v1/owo-prod/oneworldnyc-20160926-denoizy-20160924-pzg72y\\\\\\\");\\\",        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\",        \\\"phash\\\": \\\"cc36f2a57ed1b448\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 198,        \\\"class\\\": 11      },      \\\"parent\\\": {        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\"      }    },    \\\"parameter\\\": 11,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/2Lye8-mA.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/eMSa8EEg.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/HyToOzR9.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Image Priority Lane iPad\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008241" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008432," +
  				"            \"id\": \"92f395e0ee7248518da8949ca7fe5ec2\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Welcome All-Inclusive Tickets\\\",    \\\"extra\\\": \\\"Reserve All-Inclusive\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3364\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"reserve all-inclusiv\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 65,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\",        \\\"innertext\\\": \\\"reserve all-inclusiv\\\"      }    },    \\\"parameter\\\": 15,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"All-Inclusive Tickets\\\",      \\\"All-Inclusive Visit\\\",      \\\"Purchase the Ultimate Experience\\\",      \\\"Buy All-Inclusive Tickets\\\",      \\\"Experience All We Have to Offer\\\",      \\\"Experience Everything\\\",      \\\"All-Inclusive Packages\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Welcome All-Inclusive Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008432" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008567," +
  				"            \"id\": \"f6e7044467f4458daaa3bb633881a9e5\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Font color modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {\\\"prob\\\":100.0,   \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Text Color - US\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"fontcolor\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"comments\\\": \\\"\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 11      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"colors\\\": [      \\\"#1a28ea\\\",      \\\"TCELESECROF#f50f0f\\\",      \\\"#f5c2c2\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Text Color - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008567" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008774," +
  				"            \"id\": \"4672fa59b6b24762a5e73c75e5c1962a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"type\\\": \\\"lists\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"countLists\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008774" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008928," +
  				"            \"id\": \"130df0f1e8784b6ab2e86038f75ea5dd\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008928" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009109," +
  				"            \"id\": \"3a5ac9333f7b4401a10973b426f61a77\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE $55 - IT\\\",    \\\"extra\\\": \\\"$33.00\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/span[4]\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"33.00$\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE $55 - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009109" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008391," +
  				"            \"id\": \"1752f11e5d0d414aa36305eac061d3a2\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Raise Your Glass\\\",    \\\"extra\\\": \\\"Learn More\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/bar-restaurant\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 60,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\"      }    },    \\\"parameter\\\": 14,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Learn More\\\",      \\\"Get Tickets for the Family\\\",      \\\"Tickets\\\",      \\\"An Unforgettable Perspective\\\",      \\\"A View Worth the Price of Admission\\\",      \\\"Witness the Grandeur\\\",      \\\"See for Yourself\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Raise Your Glass\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008391" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008532," +
  				"            \"id\": \"2edf87ad01da46a8999785bf52fd982d\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Text - US\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"Buy Tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 11      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"Buy Tickets\\\"      }    },    \\\"parameter\\\": 17,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"BUY TICKETS\\\",      \\\"Visit Options\\\",      \\\"Ticket Options\\\",      \\\"Buy Options\\\",      \\\"Purchase Now\\\",      \\\"Reserve Now\\\",      \\\"Buy Now\\\",      \\\"Purchase Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Buy Tickets\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Text - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008532" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008740," +
  				"            \"id\": \"34d9576716d94187b4fbd50e584ffc3b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"type\\\": \\\"company\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"created\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"passThru\\\": false,    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008740" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008895," +
  				"            \"id\": \"e0cc0bdd44a14628b0d14e668d86fc34\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008895" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009077," +
  				"            \"id\": \"fc3acf9d767d4a709cffcc032b05aef7\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE $49 - IT\\\",    \\\"extra\\\": \\\"$49.00\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[3]/div/div/div[2]/div/div[1]/div[1]/span[4]\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"49.00$\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE $49 - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009077" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009213," +
  				"            \"id\": \"a99d04e3fee748ca852049f8b64782dc\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE SENIOR -IT\\\",    \\\"extra\\\": \\\"SENIOR INCLUSIVE (65+)\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[2]/div/div/div[1]/div/h1\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"SENIOR INCLUSIVE (65+)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE SENIOR -IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009213" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009327," +
  				"            \"id\": \"2305f62c06a54e84a1e5f8e163798113\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Change 7.01\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"text\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2)\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"comments\\\": \\\"\\\",    \\\"newtexts\\\": [      \\\"$7.02\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Change 7.01\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009327" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008465," +
  				"            \"id\": \"134639ebf8184b8193a9bed8c9d9017c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Notify Purchase - US\\\",    \\\"extra\\\": \\\"Purchase Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 16,        \\\"class\\\": 1      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-slider-slide__footer\\\"        ],        \\\"tag\\\": \\\"footer\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"updated\\\": true,    \\\"targets\\\": [      \\\"{\\\\\\\"type\\\\\\\":\\\\\\\"class\\\\\\\",\\\\\\\"attributes\\\\\\\":{\\\\\\\"href\\\\\\\":\\\\\\\"/en-US/buy-tickets\\\\\\\",\\\\\\\"class\\\\\\\":[\\\\\\\"s-slider-slide__button\\\\\\\"],\\\\\\\"target\\\\\\\":\\\\\\\"_self\\\\\\\",\\\\\\\"data-track-element\\\\\\\":\\\\\\\"CTA\\\\\\\",\\\\\\\"data-track-slide-position\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"data-track-title\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"a\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy tickets\\\\\\\"},\\\\\\\"counts\\\\\\\":{\\\\\\\"tag\\\\\\\":16,\\\\\\\"class\\\\\\\":1},\\\\\\\"parent\\\\\\\":{\\\\\\\"class\\\\\\\":[\\\\\\\"s-slider-slide__footer\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"footer\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy tickets\\\\\\\"}}\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Notify Purchase - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008465" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008664," +
  				"            \"id\": \"ca4df3bd7ff444dab485c630e1e34b53\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Top Line Experience - US\\\",    \\\"extra\\\": \\\"Experience\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/experience\\\",        \\\"class\\\": [          \\\"s-link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"experience\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 48,        \\\"class\\\": 18      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\",        \\\"innertext\\\": \\\"experience\\\"      }    },    \\\"parameter\\\": 18,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Experience It All\\\",      \\\"Experience The Awe\\\",      \\\"Experience The View\\\",      \\\"Experiences\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Top Line Experience - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008664" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008807," +
  				"            \"id\": \"aca9878f7d014d5795a1a37f256c3d6f\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"type\\\": \\\"reports\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"countReports\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008807" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008961," +
  				"            \"id\": \"a94213e71c1243cf8dbc69322836ca9b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE $0 - IT\\\",    \\\"extra\\\": \\\"$0.00\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[4]/div/div/div[2]/div/div[1]/div[1]/span[1]\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"0.00$\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE $0 - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008961" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009142," +
  				"            \"id\": \"16979cb594144443a7445b8470437e58\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE ADULT - IT\\\",    \\\"extra\\\": \\\"ADULT INCLUSIVE (13-64)\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[1]/div/div/div[1]/div/h1\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"INCLUSIVO ADULTO (13-64)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE ADULT - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009142" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009278," +
  				"            \"id\": \"d298e52279f048de864a36d5f2257735\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO All Inclusive Evening Buy Now\\\",    \\\"extra\\\": \\\"All Inclusive Evening Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[2]/div[5]/div[1]/div[2]/ul/li[2]/div/div[4]/button\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false,    \\\"targets\\\": [ ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO All Inclusive Evening Buy Now\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009278" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008498," +
  				"            \"id\": \"2dbf494588e845e1a43c2d978968b705\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Style Color - US\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slide-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 48,        \\\"class\\\": 1      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-slide-call-to-action\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 16,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#fbd6ac,#f98404)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Style Color - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008498" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008707," +
  				"            \"id\": \"36dc065b0e974864a7cff4d0d487af4e\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Top Line Tickets - US\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 48,        \\\"class\\\": 18      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 17,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Visit Options\\\",      \\\"Ticket Options\\\",      \\\"Buy Options\\\",      \\\"Purchase Now\\\",      \\\"Reserve Now\\\",      \\\"Buy Now\\\",      \\\"Purchase Tickets\\\",      \\\"Reserve Tickets\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Top Line Tickets - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008707" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280008857," +
  				"            \"id\": \"02010661ca5545b3817fb3f57a8a140e\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"type\\\": \\\"rules\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:49:23.094Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280008857" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009041," +
  				"            \"id\": \"bb6e52f9b2ff4e019246faffcdc1ab5b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE $33 - IT\\\",    \\\"extra\\\": \\\"$55.00\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[1]/div/div/div[2]/div/div[1]/div[1]/span[4]\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"55.00$\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE $33 - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009041" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009180," +
  				"            \"id\": \"1291ebfe33dd4f399b0f53f773e67f3c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE CHILD - IT\\\",    \\\"extra\\\": \\\"CHILD INCLUSIVE (5 and Under)\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[4]/div/div/div[1]/div/h1\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"INCLUSIVE BAMBINI (5 e meno)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE CHILD - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009180" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009307," +
  				"            \"id\": \"f478cb583b7747579ca694ecda34b8c6\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO All Inclusive Morning Buy Now\\\",    \\\"extra\\\": \\\"All Inclusive Morning Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[2]/div[5]/div[1]/div[2]/ul/li[1]/div/div[4]/button\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false,    \\\"targets\\\": [ ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO All Inclusive Morning Buy Now\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009307" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009389," +
  				"            \"id\": \"add30340aded4faa868c3c0a7776ef87\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"type\\\": \\\"company\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"test.bskinz.com\\\",    \\\"created\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"passThru\\\": false,    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"test.bskinz.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009389" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009483," +
  				"            \"id\": \"a42a95c51fa34c47b31c4b6d1096861e\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"test.bskinz.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009483" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009639," +
  				"            \"id\": \"db3d14502e1840b0be96e164b135ade6\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"type\\\": \\\"reports\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"countReports\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009639" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009245," +
  				"            \"id\": \"7de3421ab27f4fb5a17db68bcaddcaff\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO ALL-INCLUSIVE YOUTH - IT\\\",    \\\"extra\\\": \\\"YOUTH INCLUSIVE (6-12)\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/packageDetails/2577*\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[3]/div/div/div[1]/div/h1\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"GIOVANI INCLUSIVE (6-12)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO ALL-INCLUSIVE YOUTH - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009245" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009348," +
  				"            \"id\": \"44242d6fa848459498134bf142478168\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Complete Your Order\\\",    \\\"extra\\\": \\\"Complete Your Order\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"//*[@id=\\\\\\\"complete-order-btn\\\\\\\"]\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\"span.subtotal-total\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Complete Your Order\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009348" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009428," +
  				"            \"id\": \"1c8a5bb1d5d0412aa550f6fa38335f87\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"type\\\": \\\"reports\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"countReports\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"test.bskinz.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009428" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009555," +
  				"            \"id\": \"26f21a82e6a8494cb5031782deaffa98\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"type\\\": \\\"company\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"www.oldrules.com\\\",    \\\"created\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"passThru\\\": false,    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009555" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009745," +
  				"            \"id\": \"2b09375eacec48088ac35a9bc682cb30\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009745" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\",";
  /* This is the second part of the test data used to test (with Junit5) HDLmTree */
  protected static String   jsonGetPassStrPart2 =
      		"            \"created\": 1631280009368," +
  				"            \"id\": \"c0f5e44104ce4d06b76230e06ce8633c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Extract modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Get Transaction ID\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"extract\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"span.order-id\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"owo.secure.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Get Transaction ID\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009368" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009452," +
  				"            \"id\": \"6853d29892d7419a813fe5a666ab19a2\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"type\\\": \\\"rules\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"test.bskinz.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009452" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009583," +
  				"            \"id\": \"805e62efcbaa4603a969affb0e17f15c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"type\\\": \\\"lists\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"countLists\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009583" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009791," +
  				"            \"id\": \"529ed9bf123443cbad3ce5312b076d64\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009791" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009408," +
  				"            \"id\": \"744bf63584314dfcb39b26fd7a7d18d6\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"type\\\": \\\"lists\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"countLists\\\": 0,    \\\"created\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:56:43.124Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"test.bskinz.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009408" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009514," +
  				"            \"id\": \"27e1e57fb84d4b0cbe4adc554ab234ef\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"test.bskinz.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009514" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009674," +
  				"            \"id\": \"d845e55fcfaa41cd911c75b82a41bee5\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"type\\\": \\\"rules\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T22:58:55.700Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009674" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009943," +
  				"            \"id\": \"35abdb419f4e42f686db7600a47158b6\"," +
  				"            \"info\": \"{\\\"tooltip\\\": \\\"Order modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Change Order\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"order\\\",    \\\"path\\\": \\\"/en-US/*\\\",    \\\"cssselector\\\": \\\"#ticket-package > div > div\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"orders\\\": [      \\\"0,1,2,3,4,5,6\\\",      \\\"0,3,2,1,4,5,6\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Change Order\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009943" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010166," +
  				"            \"id\": \"ba12a1f926c04ffd8a2c2c9795037f90\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule is not enabled. This rule is just for testing.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Free Cheesecake\\\",    \\\"extra\\\": \\\"Stunning Views\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"#id-114 + span\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Stunning Views\\\",      \\\"Free Cheesecake\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Free Cheesecake\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010166" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010292," +
  				"            \"id\": \"1a622c09ce154fa7aa9f1c6b3ac39c28\"," +
  				"            \"info\": \"{\\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image All\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$58\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 6,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--column\\\",          \\\"s-ticket-package__card--all-inclusive\\\",          \\\"js-ticket-package__card\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"$58\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/All+Inclusive.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Z18iyHxA.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/k7kLhX9w.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010292" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010403," +
  				"            \"id\": \"79f6b99478454b9487ddf54d16e3ea3b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Siteseeing\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 6,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--row\\\",          \\\"s-ticket-package__card--pass\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"the sightseeing pass\\\"      }    },    \\\"parameter\\\": 5,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/vE6ggkZB.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/nAT0n5jy.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Siteseeing\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010403" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009887," +
  				"            \"id\": \"88bdeeead2d54e189345aad9d34bb27b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule is not enabled. This rule is just for testing.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Change Experience\\\",    \\\"extra\\\": \\\"NYC Inspired Cuisine\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"NYC Inspired Cuisine\\\",      \\\"NYC Cusine for Oscar and Mike\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Change Experience\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009887" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010065," +
  				"            \"id\": \"6d2aaae58196480aaf8d069194a9feac\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Modify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled to enable the iFrame to work\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Fix Iframe\\\",    \\\"extra\\\": \\\"fixiframesrc\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"modify\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"#override\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Fix Iframe\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010065" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010269," +
  				"            \"id\": \"7da86dd60baf4ff293e727894e56a3bb\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient Stan\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2447\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 61,        \\\"class\\\": 7      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#b8cdf7,#01cbf2)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient Stan\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010269" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010361," +
  				"            \"id\": \"0c8dc114fd3249a0a687ea2d43f1bde6\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule is not enabled. This rule is just for testing.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image NYC Inspired\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$73\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 6,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--row\\\",          \\\"s-ticket-package__card--dinner\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"$73\\\"      }    },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//media.istockphoto.com/photos/electronic-circuit-network-grunge-background-picture-id513311684\\\",      \\\"//media.istockphoto.com/photos/businesswoman-hand-touch-word-innovation-on-screen-business-concept-picture-id638106552\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image NYC Inspired\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010361" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010513," +
  				"            \"id\": \"1a2f8cc9d524420995a45e717f736679\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled becuase of a Mastercard contract\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Remove Mastercard\\\",    \\\"extra\\\": \\\"Mastercard\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Mastercard\\\",      \\\"A Guided Experience\\\",      \\\"A Guided Experience\\\",      \\\"A Guided Experience\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Remove Mastercard\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010513" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010651," +
  				"            \"id\": \"a8a5d1318d644954a01bfd2718715af0\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Top Line Change Text\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-link\\\",          \\\"is-active\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 56,        \\\"class\\\": 18      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Buy Tickets Now\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Ticketss\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Top Line Change Text\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010651" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280009991," +
  				"            \"id\": \"e1bd66e67d19402aada30b592ffcaddf\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Order modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled. All foreign language rules have been disabled.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Change Order - IT\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"order\\\",    \\\"path\\\": \\\"/it/acquista-biglietti\\\",    \\\"cssselector\\\": \\\"#ticket-package > div > div\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"orders\\\": [      \\\"0,1,2,3,4,5,6\\\",      \\\"0,3,2,1,4,5,6\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Change Order - IT\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280009991" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010200," +
  				"            \"id\": \"83b8121daf654fe5a94d399099ef3509\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient All\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 61,        \\\"class\\\": 7      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#fbd6ac,#f98404)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010200" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010314," +
  				"            \"id\": \"c0078c1296534a8184e8f691f3ab2b47\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Combo\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$48\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 6,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--column\\\",          \\\"s-ticket-package__card--combination\\\",          \\\"js-ticket-package__card\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"$48\\\"      }    },    \\\"parameter\\\": 3,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Combination.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/JVz8f1ng.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/2Lye8-mA.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010314" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010436," +
  				"            \"id\": \"99b61720d6d54a61be4d7422587f562a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Stan\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$38\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 6,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--column\\\",          \\\"s-ticket-package__card--standard\\\",          \\\"js-ticket-package__card\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"$38\\\"      }    },    \\\"parameter\\\": 6,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/General+Admission.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/q5cJijUw.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/RWgd8Qjw.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Stan\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010436" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010028," +
  				"            \"id\": \"e0c18f072e1141e982a9eccacf881443\"," +
  				"            \"info\": \"{\\\"tooltip\\\": \\\"Attribute modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Fix Href All\\\",    \\\"extra\\\": \\\"href/useproxyhost\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"attribute\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Fix Href All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010028" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010247," +
  				"            \"id\": \"c656e6a8aa334e5bba449b0673962ea1\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient Combo\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 61,        \\\"class\\\": 7      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#f4a5b9,#f60441)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010247" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010337," +
  				"            \"id\": \"704974451ec84a9db48b3e336dd735b8\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Image Learn More Group\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"m-promo__image\\\",          \\\"c-image-cloudinary\\\",          \\\"c-image-cloudinary--crop-fit\\\",          \\\"c-image-cloudinary--as-background\\\"        ],        \\\"style\\\": \\\"background-image: url(\\\\\\\"https://legends-cloudinary.corebine.com/legends-production/image/upload/c_fit,dpr_2.0,f_webp,g_center,q_auto,w_3840/v1/owo-prod/141027_EJ_dusk_OWO_pano_0079-Edit-2\\\\\\\");\\\",        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\",        \\\"phash\\\": \\\"ccc644cc94b2298a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 130,        \\\"class\\\": 1      },      \\\"parent\\\": {        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\"      }    },    \\\"parameter\\\": 4,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/141027_EJ_dusk_OWO_pano_0079-Edit-2.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/C3bEqZ3Q.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/qHerdAHi.jpg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Image Learn More Group\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010337" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010476," +
  				"            \"id\": \"51c866acf02b4b789d2243c26a3ac6a4\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Purchase All\\\",    \\\"extra\\\": \\\"Buy All\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"style\\\": \\\"background-image: linear-gradient(to left, rgb(251, 214, 172), rgb(249, 132, 4));\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\"{\\\\\\\"type\\\\\\\":\\\\\\\"class\\\\\\\",\\\\\\\"attributes\\\\\\\":{\\\\\\\"href\\\\\\\":\\\\\\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\\\\\",\\\\\\\"class\\\\\\\":[\\\\\\\"b-call-to-action__link\\\\\\\"],\\\\\\\"style\\\\\\\":\\\\\\\"background-image: linear-gradient(to left, rgb(251, 214, 172), rgb(249, 132, 4));\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"a\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"},\\\\\\\"counts\\\\\\\":{\\\\\\\"tag\\\\\\\":55,\\\\\\\"class\\\\\\\":6},\\\\\\\"parent\\\\\\\":{\\\\\\\"class\\\\\\\":[\\\\\\\"b-link\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"div\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"}}\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Purchase All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010476" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010619," +
  				"            \"id\": \"52000cde0340465ca8a850ea8cc3c342\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text Stan\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2447\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"style\\\": \\\"background-image: linear-gradient(to left, rgb(184, 205, 247), rgb(1, 203, 242));\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text Stan\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010619" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010548," +
  				"            \"id\": \"b647296313844440acf4fc96052837e4\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text All\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010548" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010683," +
  				"            \"id\": \"d33246c64da64160b69472f7106f0671\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Beneath Buy Tickets\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/div/div/div[2]/div/div/div/nav/ul/li[1]/section/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 8,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"See for Yourself\\\",      \\\"Witness the Grandeur\\\",      \\\"A View Worth the Price of Admission\\\",      \\\"An Unforgettable Perspective\\\",      \\\"Tickets\\\",      \\\"Get Tickets for the Family\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Beneath Buy Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010683" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010872," +
  				"            \"id\": \"6ca54b2a57e545ef8c9d4dd629e523af\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule does not work. The image perceptual hash value is wrong.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Image Priority Lane iPad - Fail\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"m-promo__image\\\",          \\\"c-image-cloudinary\\\",          \\\"c-image-cloudinary--crop-fit\\\",          \\\"c-image-cloudinary--as-background\\\"        ],        \\\"style\\\": \\\"background-image: url(\\\\\\\"https://legends-cloudinary.corebine.com/legends-production/image/upload/c_fit,dpr_2.0,f_webp,g_center,q_auto,w_3840/v1/owo-prod/141027_EJ_dusk_OWO_pano_0067-Edit\\\\\\\");\\\",        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\",        \\\"phash\\\": \\\"ce273873bf9c76fb\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 176,        \\\"class\\\": 10      },      \\\"parent\\\": {        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\"      }    },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/2Lye8-mA.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/HyToOzR9.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/eMSa8EEg.jpeg\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Image Priority Lane iPad - Fail\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010872" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011231," +
  				"            \"id\": \"d2debae5118942d5b2c347a0be6aec49\"," +
  				"            \"info\": \"{\\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Welcome All-Inclusive Tickets\\\",    \\\"extra\\\": \\\"All-Inclusive Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/main/section[3]/div/div[2]/div/div/div/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 15,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"All-Inclusive Tickets\\\",      \\\"All-Inclusive Visit\\\",      \\\"Purchase the Ultimate Experience\\\",      \\\"Buy All-Inclusive Tickets\\\",      \\\"Experience All We Have to Offer\\\",      \\\"Experience Everything\\\",      \\\"All-Inclusive Packages\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Welcome All-Inclusive Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011231" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011462," +
  				"            \"id\": \"7c8fa46bd9134ae0aa46392bd58df7f5\"," +
  				"            \"info\": \"{\\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule is not enabled. This rule caused problems.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Style Width - US\\\",    \\\"extra\\\": \\\"width\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"100px\\\",      \\\"150px\\\",      \\\"200px\\\",      \\\"250px\\\",      \\\"300px\\\",      \\\"350px\\\",      \\\"400px\\\",      \\\"450px\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Style Width - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011462" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010583," +
  				"            \"id\": \"febff95bccb24247b8d7709845954602\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text Combo\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"style\\\": \\\"background-image: linear-gradient(to left, rgb(244, 165, 185), rgb(246, 4, 65));\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 55,        \\\"class\\\": 6      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010583" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010718," +
  				"            \"id\": \"2cc1126aac6d4ee7b36a7ed5277424a1\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience City Pulse Buy Tickets\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/main/section[8]/div/div[2]/div/div[2]/div/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 9,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Buy the Experience\\\",      \\\"Experience Our Experts\\\",      \\\"Buy a Lesson in History\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience City Pulse Buy Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010718" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010945," +
  				"            \"id\": \"aae76b066f6247eaa10918e784345d5f\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Mastercard Reserve VIP\\\",    \\\"extra\\\": \\\"Reserve a VIP Tour\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/main/section[9]/div/div[2]/div/div/div/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 12,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Reserve a VIP Tour\\\",      \\\"Visit As a VIP\\\",      \\\"Buy a VIP Experience\\\",      \\\"Tour As a VIP\\\",      \\\"Reserve More Than Just the View\\\",      \\\"Mastercard Tickets\\\",      \\\"Buy an Exclusive Experience\\\",      \\\"Take Advantage of This Offer\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Mastercard Reserve VIP\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010945" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011297," +
  				"            \"id\": \"c6cee02c38ba4366ac5f98f897bf2bbc\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled. All foreign language rules have been disabled.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Purchase - DE\\\",    \\\"extra\\\": \\\"Purchase Tickets In German for Chris Cole\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\".s-slide-call-to-action__link\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Purchase - DE\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011297" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011526," +
  				"            \"id\": \"789f2b64358a406a8cc4302084d245e9\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled. All foreign language rules have been disabled.\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Text - DE\\\",    \\\"extra\\\": \\\"TICKETS KAUFEN\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"TICKETS KAUFEN\\\",      \\\"Tickets Kaugen 1\\\",      \\\"Tickets Kaugen 2\\\",      \\\"Tickets Kaugen 3\\\",      \\\"Tickets Kaugen 4\\\",      \\\"Tickets Kaugen 5\\\",      \\\"Tickets Kaugen 6\\\",      \\\"Tickets Kaugen 7\\\",      \\\"Tickets Kaugen 8\\\",      \\\"Tickets Kaugen 9\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Text - DE\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011526" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011743," +
  				"            \"id\": \"cbd3bf87f7064e5187de0c1c256f9449\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2020-12-22T18:08:25.501623300Z\\\",    \\\"lastmodified\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"www.oneworldobservatory.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"company\\\",    \\\"updated\\\": false,    \\\"passThru\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011743" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012000," +
  				"            \"id\": \"430d32ec5cb34d3cb6665dff8e94338d\"," +
  				"            \"info\": \"{\\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2020-12-22T18:08:25.501623300Z\\\",    \\\"lastmodified\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"countReports\\\": 3,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"reports\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012000" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010751," +
  				"            \"id\": \"7f95dec91220414798e817e0a94b408e\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Hero Buy Tickets\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/main/section[1]/div/div/div/section/div/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 10,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Ticket Options\\\",      \\\"Ticket Buying Options\\\",      \\\"Ticket Package Options\\\",      \\\"Ticket Purchase Options\\\",      \\\"Witness This View\\\",      \\\"Visit Now\\\",      \\\"Reserve Your Experience\\\",      \\\"Join Us on Top of the World\\\",      \\\"Experience the Wonder\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Experience Now\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Hero Buy Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010751" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011132," +
  				"            \"id\": \"f9deef48000c40a7936533e1898951ce\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Priority Lane Combination Tickets\\\",    \\\"extra\\\": \\\"Combination Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div[1]/main/section[6]/div/div[2]/div/div/div/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 13,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Combination Tickets\\\",      \\\"Buy an Upgraded Experience\\\",      \\\"Purchase a Combination Ticket\\\",      \\\"Buy a Combination Ticket\\\",      \\\"Combo Tickets\\\",      \\\"More Than Just the View\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Priority Lane Combination Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011132" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011358," +
  				"            \"id\": \"9189bde5de574931bb6d030178276a8d\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Purchase - US\\\",    \\\"extra\\\": \\\"Purchase Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\".s-slide-call-to-action__link\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Purchase - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011358" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011572," +
  				"            \"id\": \"5aefb889b7cc4167ac3f1e1aabe4028f\"," +
  				"            \"info\": \"{ \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Text - US\\\",    \\\"extra\\\": \\\"BUY TICKETS\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 17,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"BUY TICKETS\\\",      \\\"Visit Options\\\",      \\\"Ticket Options\\\",      \\\"Buy Options\\\",      \\\"Purchase Now\\\",      \\\"Reserve Now\\\",      \\\"Buy Now\\\",      \\\"Purchase Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Buy Tickets\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Text - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011572" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011831," +
  				"            \"id\": \"53a95ba5b0a04637b298045362ecb9c5\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2020-12-22T18:08:25.501623300Z\\\",    \\\"lastmodified\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countLists\\\": 1,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lists\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011831" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012061," +
  				"            \"id\": \"9eb9812f2a284e139b5047ecaaf1fe12\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Report node\\\",  \\\"type\\\": \\\"report\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.813373400Z\\\",    \\\"countLines\\\": 4,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Report000005\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"report\\\",    \\\"updated\\\": false,    \\\"passThru\\\": false,    \\\"reportType\\\": \\\"Check website\\\",    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012061" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012361," +
  				"            \"id\": \"4afad3c964b24ec6a576f972fca011e0\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"countLines\\\": 4,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Overall\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Overall\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012361" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280010793," +
  				"            \"id\": \"410a6f92f2a740f2aa7ff6bc6a7a3e02\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Image Priority Lane iPad\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"m-promo__image\\\",          \\\"c-image-cloudinary\\\",          \\\"c-image-cloudinary--crop-fill\\\",          \\\"c-image-cloudinary--as-background\\\"        ],        \\\"style\\\": \\\"background-image: url(\\\\\\\"https://legends-cloudinary.corebine.com/legends-production/image/upload/c_fill,dpr_2.0,f_webp,g_auto,q_auto,w_1170/v1/owo-prod/oneworldnyc-20160926-denoizy-20160924-pzg72y\\\\\\\");\\\",        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\",        \\\"phash\\\": \\\"cc36f2a57ed1b448\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 198,        \\\"class\\\": 11      },      \\\"parent\\\": {        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"\\\"      }    },    \\\"parameter\\\": 11,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/2Lye8-mA.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/eMSa8EEg.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/HyToOzR9.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Image Priority Lane iPad\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280010793" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011177," +
  				"            \"id\": \"23587e124b654df3abb06293a6789d36\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Raise Your Glass\\\",    \\\"extra\\\": \\\"Learn More\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/bar-restaurant\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 60,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"section\\\"      }    },    \\\"parameter\\\": 14,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Learn More\\\",      \\\"Get Tickets for the Family\\\",      \\\"Tickets\\\",      \\\"An Unforgettable Perspective\\\",      \\\"A View Worth the Price of Admission\\\",      \\\"Witness the Grandeur\\\",      \\\"See for Yourself\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Raise Your Glass\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011177" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011418," +
  				"            \"id\": \"b2dd79dd95464a56a37c22110997ea0c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Style Color - US\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 16,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Style Color - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011418" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011626," +
  				"            \"id\": \"c80d486a374c40ce93d37c5a489f7cdd\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Top Line Experience - US\\\",    \\\"extra\\\": \\\"Experience\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 18,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Experience\\\",      \\\"Experience\\\",      \\\"Experience\\\",      \\\"Experience\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Top Line Experience - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011626" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011879," +
  				"            \"id\": \"fe4b6d6b2cc5456a83dd95e3522f2845\"," +
  				"            \"info\": \"{   \\\"tooltip\\\": \\\"Ignore-list node\\\",  \\\"type\\\": \\\"list\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-02T21:41:08.332Z\\\",    \\\"lastmodified\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countIgnores\\\": 1,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"FirstList\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"list\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Ignore Lists\\\",    \\\"FirstList\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011879" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012131," +
  				"            \"id\": \"73b0a4ea268a458c8759ea930dd3ab09\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"List modification\\\",  \\\"type\\\": \\\"list\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-02T21:41:08.332Z\\\",    \\\"lastmodified\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countIgnores\\\": 1,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"FirstList\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"list\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"FirstList\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012131" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011684," +
  				"            \"id\": \"083232d949034d10addcd435f4dff217\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Top Line Tickets - US\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 17,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Visit Options\\\",      \\\"Ticket Options\\\",      \\\"Buy Options\\\",      \\\"Purchase Now\\\",      \\\"Reserve Now\\\",      \\\"Buy Now\\\",      \\\"Purchase Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Buy Tickets\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oldrules.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Top Line Tickets - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011684" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280011953," +
  				"            \"id\": \"7187f0a63bca4f7081655c447cfefbb6\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-list entry node\\\",  \\\"type\\\": \\\"ignore\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-03T00:43:34.639Z\\\",    \\\"lastmodified\\\": \\\"2021-01-03T00:43:34.639Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"*\\\",    \\\"scriptId\\\": \\\"*\\\",    \\\"testCase\\\": \\\"*\\\",    \\\"stepNumber\\\": \\\"*\\\",    \\\"description\\\": \\\"*\\\",    \\\"language\\\": \\\"*\\\",    \\\"ticketPackage\\\": \\\"*\\\",    \\\"testResults\\\": \\\"*\\\",    \\\"detailsOne\\\": \\\"*\\\",    \\\"detailsTwo\\\": \\\"*\\\",    \\\"detailsThree\\\": \\\"*\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"FirstEntry\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"ignore\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"First Entry\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Ignore Lists\\\",    \\\"FirstList\\\",    \\\"FirstEntry\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280011953" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012185," +
  				"            \"id\": \"1115fe1ae5b04224a789806ce95ef0ad\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore modification\\\",  \\\"type\\\": \\\"ignore\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-03T00:43:34.639Z\\\",    \\\"lastmodified\\\": \\\"2021-01-03T00:43:34.639Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"*\\\",    \\\"scriptId\\\": \\\"*\\\",    \\\"testCase\\\": \\\"*\\\",    \\\"stepNumber\\\": \\\"*\\\",    \\\"description\\\": \\\"*\\\",    \\\"language\\\": \\\"*\\\",    \\\"ticketPackage\\\": \\\"*\\\",    \\\"testResults\\\": \\\"*\\\",    \\\"detailsOne\\\": \\\"*\\\",    \\\"detailsTwo\\\": \\\"*\\\",    \\\"detailsThree\\\": \\\"*\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"FirstEntry\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"ignore\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"First Entry\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"FirstList\\\",    \\\"FirstEntry\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012185" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012496," +
  				"            \"id\": \"49e906f28a5a46feb4153bf9c7fbeec9\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:53:23.166605\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 12,    \\\"description\\\": \\\"Click credit\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"No elements matched the XPath expression - /html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[3]/div/div/div[1]/div/div/label\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000002\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Overall\\\",    \\\"Line000002\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012496" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012812," +
  				"            \"id\": \"ba99bc60feae44ef96573cc42aad4e46\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:52:53.149811\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 11,    \\\"description\\\": \\\"Click shipping\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"No elements matched the XPath expression - //*[@id=\\\\\\\"shipping-continue-shipping-btn\\\\\\\"]\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000001\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Valid\\\",    \\\"Line000001\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012812" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013054," +
  				"            \"id\": \"4494a36852964227aa14e7e3db50beaa\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Report node\\\",  \\\"type\\\": \\\"report\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.112618Z\\\",    \\\"countLines\\\": 3,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Report000006\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"report\\\",    \\\"updated\\\": false,    \\\"passThru\\\": false,    \\\"reportType\\\": \\\"Check error\\\",    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013054" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013335," +
  				"            \"id\": \"fa4a73e48774485ab9adc61f0d9e3ee4\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"Traceback (most recent call last):\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000002\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Overall\\\",    \\\"Line000002\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013335" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012276," +
  				"            \"id\": \"317e54e0c2934b7d9338c30ace5629e4\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"countLines\\\": 0,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Invalid\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Invalid\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012276" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012553," +
  				"            \"id\": \"9c47e269ee98450a845095633dc73d22\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:53:53.177280\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 13,    \\\"description\\\": \\\"Click continue\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"Message: Unable to locate element: [id=\\\\\\\"billing-continue-btn\\\\\\\"]\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000003\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Overall\\\",    \\\"Line000003\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012553" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012872," +
  				"            \"id\": \"9cf421b781c840f0a267b3e787918d6e\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:53:23.166605\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 12,    \\\"description\\\": \\\"Click credit\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"No elements matched the XPath expression - /html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[3]/div/div/div[1]/div/div/label\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000002\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Valid\\\",    \\\"Line000002\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012872" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013170," +
  				"            \"id\": \"9404c58ef25c40fa969a7fb55df34826\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.113630400Z\\\",    \\\"countLines\\\": 0,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Invalid\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Invalid\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013170" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013380," +
  				"            \"id\": \"56a70ce0adf84c52b395bdb74b77656a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"  File \\\\\\\"C:\\\\\\\\Users\\\\\\\\pscha\\\\\\\\Documents\\\\\\\\Visual_Studio_Code\\\\\\\\Projects\\\\\\\\PythonApps\\\\\\\\PythonApps\\\\\\\\SeleniumTest2.py\\\\\\\", line 3, in <module>\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000003\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Overall\\\",    \\\"Line000003\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013380" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013762," +
  				"            \"id\": \"14b0dc3f371d4dcd9cfc7a833ffabcae\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Report node\\\",  \\\"type\\\": \\\"report\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countLines\\\": 3,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Report000007\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"report\\\",    \\\"updated\\\": false,    \\\"passThru\\\": false,    \\\"reportType\\\": \\\"Check error\\\",    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013762" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013916," +
  				"            \"id\": \"b0847f117fa04c2a89886cb039633774\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"Traceback (most recent call last):\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000002\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Overall\\\",    \\\"Line000002\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013916" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014067," +
  				"            \"id\": \"3d9345320d8543628b503b099d2b9335\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countLines\\\": 0,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Valid\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Valid\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014067" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012438," +
  				"            \"id\": \"b18e75dd1ea54615a28ed8a3760d8d75\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:52:53.149811\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 11,    \\\"description\\\": \\\"Click shipping\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"No elements matched the XPath expression - //*[@id=\\\\\\\"shipping-continue-shipping-btn\\\\\\\"]\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000001\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Overall\\\",    \\\"Line000001\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012438" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012732," +
  				"            \"id\": \"5471a03ef0644e18a30155f5e9d4c2d4\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"countLines\\\": 4,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Valid\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Valid\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012732" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012969," +
  				"            \"id\": \"ad94bfea24ac41a689e33076c4072878\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:54:23.194166\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 14,    \\\"description\\\": \\\"No pre-populated data\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"No elements matched the XPath expression - //*[@id=\\\\\\\"fname\\\\\\\"]\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000004\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Valid\\\",    \\\"Line000004\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012969" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013256," +
  				"            \"id\": \"138472b109b1409283e15471241e0beb\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.113630400Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:01.113630400Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Execute\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"Process exited with an error: 1 (Exit value: 1)\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000001\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Overall\\\",    \\\"Line000001\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013256" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013531," +
  				"            \"id\": \"bd53f2d2d62b476f93ab19e1310f72b4\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"ModuleNotFoundError: No module named 'cv2222'\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000005\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Overall\\\",    \\\"Line000005\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013531" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013865," +
  				"            \"id\": \"08f62d0be1f74c3396d717f986028577\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countLines\\\": 5,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Overall\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Overall\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\",";
  /* This is the third part of the test data used to test (with Junit5) HDLmTree */
  protected static String   jsonGetPassStrPart3 =
      		"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013865" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013990," +
  				"            \"id\": \"10cc032ab0f84f8284c6f80bdae85dff\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"    import cv2222\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000004\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Overall\\\",    \\\"Line000004\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013990" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014123," +
  				"            \"id\": \"7ccdf3be93b84da9bba2d7d8bb6d6536\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014123" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012619," +
  				"            \"id\": \"1f6e86944d624945967abd303abae8d7\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:54:23.194166\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 14,    \\\"description\\\": \\\"No pre-populated data\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"No elements matched the XPath expression - //*[@id=\\\\\\\"fname\\\\\\\"]\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000004\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Overall\\\",    \\\"Line000004\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012619" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280012923," +
  				"            \"id\": \"a1d519de799543cb97d3ce4c230a9148\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T16:54:24.814372700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T10:53:53.177280\\\",    \\\"scriptId\\\": 2,    \\\"testCase\\\": 4,    \\\"stepNumber\\\": 13,    \\\"description\\\": \\\"Click continue\\\",    \\\"ticketPackage\\\": \\\"All\\\",    \\\"testResults\\\": \\\"Message: Unable to locate element: [id=\\\\\\\"billing-continue-btn\\\\\\\"]\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000003\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\",    \\\"language\\\": \\\"*\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000005\\\",    \\\"Valid\\\",    \\\"Line000003\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280012923" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013214," +
  				"            \"id\": \"f7679cbede884a44a87a1dd0a47b4629\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.113630400Z\\\",    \\\"countLines\\\": 5,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Overall\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Overall\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013214" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013427," +
  				"            \"id\": \"e273ba44edc241b8994dc0c66b33dcf2\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:01.114629700Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"    import cv2222\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000004\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Overall\\\",    \\\"Line000004\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013427" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013820," +
  				"            \"id\": \"2005ff994b9b421cbb9ba692e9b39d55\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countLines\\\": 0,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Invalid\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Invalid\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013820" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013947," +
  				"            \"id\": \"698be74b22a4430c8f9de67e682574df\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"  File \\\\\\\"C:\\\\\\\\Users\\\\\\\\pscha\\\\\\\\Documents\\\\\\\\Visual_Studio_Code\\\\\\\\Projects\\\\\\\\PythonApps\\\\\\\\PythonApps\\\\\\\\SeleniumTest2.py\\\\\\\", line 3, in <module>\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000003\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Overall\\\",    \\\"Line000003\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013947" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014101," +
  				"            \"id\": \"170c8b290f76488cbee7aa6a2f144c91\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2020-12-15T04:27:52.762Z\\\",    \\\"lastmodified\\\": \\\"2020-12-14T23:24:37.408Z\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"rules\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014101" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014189," +
  				"            \"id\": \"9481a781ed1d4077b0e4d80c2c547131\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Attribute modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Fix Href All\\\",    \\\"extra\\\": \\\"href/useproxyhost\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"attribute\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"data-accesso-package\\\": \\\"3145\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Fix Href All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014189" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013627," +
  				"            \"id\": \"d863fa312bbb439db5fb3f2a95737e62\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Lines modification\\\",  \\\"type\\\": \\\"lines\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:01.113630400Z\\\",    \\\"countLines\\\": 0,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Valid\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"lines\\\",    \\\"updated\\\": false,    \\\"dummyTable\\\": \\\"dummyTable\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000006\\\",    \\\"Valid\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013627" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280013895," +
  				"            \"id\": \"deeb39debc1b46e59611b91e2b0c92ae\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Execute\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"Process exited with an error: 1 (Exit value: 1)\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000001\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Overall\\\",    \\\"Line000001\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280013895" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014033," +
  				"            \"id\": \"fa7092513e3b41c6b6d53a0ee6383d53\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Line modification\\\",  \\\"type\\\": \\\"line\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"createdFromVerificationCheck\\\": \\\"2021-01-04T18:12:28.758342300Z\\\",    \\\"scriptId\\\": 0,    \\\"testCase\\\": 0,    \\\"stepNumber\\\": 0,    \\\"description\\\": \\\"Stderr\\\",    \\\"language\\\": \\\"EN\\\",    \\\"ticketPackage\\\": \\\"None\\\",    \\\"testResults\\\": \\\"ModuleNotFoundError: No module named 'cv2222'\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Line000005\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"line\\\",    \\\"updated\\\": false,    \\\"detailsOne\\\": \\\"\\\",    \\\"detailsTwo\\\": \\\"\\\",    \\\"detailsThree\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Reports\\\",    \\\"Report000007\\\",    \\\"Overall\\\",    \\\"Line000005\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014033" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014146," +
  				"            \"id\": \"556c9cf33b644b6d81f3f829e9d5e8be\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014146" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014230," +
  				"            \"id\": \"5d49ff1f72ae481dac8054c8840709e3\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient Combo\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#f4a5b9,#f60441)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014230" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014311," +
  				"            \"id\": \"11b48ea18b6e4ed1b6aed9a86b7e4f4a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Notify Purchase Combo\\\",    \\\"extra\\\": \\\"Buy Combo\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\"{\\\\\\\"type\\\\\\\":\\\\\\\"class\\\\\\\",\\\\\\\"attributes\\\\\\\":{\\\\\\\"href\\\\\\\":\\\\\\\"/en/tickets?package_id=2642\\\\\\\",\\\\\\\"class\\\\\\\":[\\\\\\\"b-call-to-action__link\\\\\\\"],\\\\\\\"style\\\\\\\":\\\\\\\"background-image: linear-gradient(to left, rgb(240, 91, 131), rgb(0, 63, 108)); background-repeat: no-repeat; background-size: cover;\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"a\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"},\\\\\\\"counts\\\\\\\":{\\\\\\\"tag\\\\\\\":28,\\\\\\\"class\\\\\\\":8},\\\\\\\"parent\\\\\\\":{\\\\\\\"class\\\\\\\":[\\\\\\\"b-link\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"div\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"}}\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Notify Purchase Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014311" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014169," +
  				"            \"id\": \"6dffcfb98a8d4b6781963d44f4f09534\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Order modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Change Order\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"order\\\",    \\\"path\\\": \\\"/en-US/*\\\",    \\\"cssselector\\\": \\\"#ticket-package > div > div\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"parameter\\\": 0,    \\\"updated\\\": false,    \\\"orders\\\": [      \\\"0,1,2,3,4,5,6\\\",      \\\"0,3,2,1,4,5,6\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Change Order\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014169" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014251," +
  				"            \"id\": \"b0ced19942d44dbfb8931540116520f9\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient Dll\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"data-accesso-package\\\": \\\"3145\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#fbd6ac,#f98404)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient Dll\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014251" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014332," +
  				"            \"id\": \"ade116eb7c2145319d2942be24066ab0\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Notify Purchase Standard\\\",    \\\"extra\\\": \\\"Buy Stan\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2447\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\"{\\\\\\\"type\\\\\\\":\\\\\\\"class\\\\\\\",\\\\\\\"attributes\\\\\\\":{\\\\\\\"href\\\\\\\":\\\\\\\"/en/tickets?package_id=2447\\\\\\\",\\\\\\\"class\\\\\\\":[\\\\\\\"b-call-to-action__link\\\\\\\"],\\\\\\\"style\\\\\\\":\\\\\\\"background-image: linear-gradient(to left, rgb(240, 91, 131), rgb(0, 63, 108)); background-repeat: no-repeat; background-size: cover;\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"a\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"},\\\\\\\"counts\\\\\\\":{\\\\\\\"tag\\\\\\\":28,\\\\\\\"class\\\\\\\":8},\\\\\\\"parent\\\\\\\":{\\\\\\\"class\\\\\\\":[\\\\\\\"b-link\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"div\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"}}\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Notify Purchase Standard\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014332" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014469," +
  				"            \"id\": \"d040ed20269f4266a7cb250616839dad\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Route Image Learn More\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-promo__wrapper\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"click below to learn\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 133,        \\\"class\\\": 2      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-promo\\\"        ],        \\\"data-reactroot\\\": \\\"\\\",        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"click below to learn\\\"      }    },    \\\"parameter\\\": 3,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/141027_EJ_dusk_OWO_pano_0079-Edit-2.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/C3bEqZ3Q.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/qHerdAHi.jpg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Route Image Learn More\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014469" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014618," +
  				"            \"id\": \"f5d57bf7c7534f519b6ab918f3ed8207\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text Standard\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2447\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 4,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text Standard\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014618" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014210," +
  				"            \"id\": \"b49069ea28254e72bde811dab57ed35c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Modify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled to enable the iFrame to work\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Fix Iframe\\\",    \\\"extra\\\": \\\"fixiframesrc\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"modify\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"#override\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Fix Iframe\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014210" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014290," +
  				"            \"id\": \"4e952fce798844ccb2b978362ffaceb8\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Notify Purchase All\\\",    \\\"extra\\\": \\\"Buy All\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"data-accesso-package\\\": \\\"3145\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\"{\\\\\\\"type\\\\\\\":\\\\\\\"class\\\\\\\",\\\\\\\"attributes\\\\\\\":{\\\\\\\"href\\\\\\\":\\\\\\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\\\\\",\\\\\\\"class\\\\\\\":[\\\\\\\"b-call-to-action__link\\\\\\\"],\\\\\\\"data-accesso-package\\\\\\\":\\\\\\\"3145\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"a\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"},\\\\\\\"counts\\\\\\\":{\\\\\\\"tag\\\\\\\":28,\\\\\\\"class\\\\\\\":8},\\\\\\\"parent\\\\\\\":{\\\\\\\"class\\\\\\\":[\\\\\\\"b-link\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"div\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy now\\\\\\\"}}\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Notify Purchase All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014290" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014402," +
  				"            \"id\": \"3ef2b2f28eeb4f8f902c6c340b6e471e\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Offer Image Combo\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header__container\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"$53\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 133,        \\\"class\\\": 4      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$53\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/aolson__.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Combination.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Combination.jpeg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/2Lye8-mA.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/bkawahara.jpg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Offer Image Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014402" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014533," +
  				"            \"id\": \"08c2e6a658f64a54a7c95e672b920780\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text All\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"data-accesso-package\\\": \\\"3145\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 4,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014533" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014270," +
  				"            \"id\": \"2fff9376162644bdbc2a6bb3fe0a2245\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Gradient Standard\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2447\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 1,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#b8cdf7,#01cbf2)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Gradient Standard\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014270" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014371," +
  				"            \"id\": \"479ff75fe57e4a14a36f5466cedcde07\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Offer Image All\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header__container\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"$63\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 133,        \\\"class\\\": 4      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$63\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/Combination.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/20201027+141027_EJ_dusk_OWO_pano_0086-Edit.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/20201027+141027_EJ_dusk_OWO_pano_0058-Edit.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/All+Inclusive.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/k7kLhX9w.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Offer Image All\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014371" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014501," +
  				"            \"id\": \"f2990de2075a42b49610bb8fa5edefbf\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Route Image Siteseeing\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 9,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package__card\\\",          \\\"s-ticket-package__card--row\\\",          \\\"s-ticket-package__card--pass\\\"        ],        \\\"tag\\\": \\\"article\\\",        \\\"innertext\\\": \\\"the sightseeing pass\\\"      }    },    \\\"parameter\\\": 20,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/vE6ggkZB.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/nAT0n5jy.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Route Image Siteseeing\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014501" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014648," +
  				"            \"id\": \"3cc6d211e42242f691d1f4633bc365a8\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Top Line Change Text\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 41      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-navigation__menu-link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 5,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Buy Tickets Now\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Top Line Change Text\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014648" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014427," +
  				"            \"id\": \"3ef445ec9a3c4799a63a5c4b90a342de\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Offer Image Standard\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header__container\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"$43\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 133,        \\\"class\\\": 4      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-ticket-package-header\\\"        ],        \\\"tag\\\": \\\"header\\\",        \\\"innertext\\\": \\\"$43\\\"      }    },    \\\"parameter\\\": 2,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/dsc_5191_retouch.jpg\\\",      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/q5cJijUw.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/General+Admission.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/20201027+141027_EJ_dusk_OWO_pano_0087-Edit.jpg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/joshbarron.jpg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Offer Image Standard\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014427" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014585," +
  				"            \"id\": \"aac8ca6281bc42be88f523fbbd79f977\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Text Combo\\\",    \\\"extra\\\": \\\"Buy Now\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/buy-tickets\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"b-call-to-action__link\\\"        ],        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy now\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 28,        \\\"class\\\": 8      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"b-link\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"buy now\\\"      }    },    \\\"parameter\\\": 4,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Now\\\",      \\\"Buy Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Purchase Tickets\\\",      \\\"Buy Now\\\",      \\\"Reserve Now\\\",      \\\"Purchase Now\\\",      \\\"Buy Options\\\",      \\\"Ticket Options\\\",      \\\"Visit Options\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Text Combo\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014585" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014702," +
  				"            \"id\": \"aaab96e3b95845cc976c21c6179beb10\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Mastercard Reserve VIP\\\",    \\\"extra\\\": \\\"Reserve a VIP Tour\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"reserve a vip tour\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 18      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=3205\\\",        \\\"class\\\": [          \\\"s-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"reserve a vip tour\\\"      }    },    \\\"parameter\\\": 20,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Reserve a VIP Tour\\\",      \\\"Visit As a VIP\\\",      \\\"Buy a VIP Experience\\\",      \\\"Tour As a VIP\\\",      \\\"Reserve More Than Just the View\\\",      \\\"Mastercard Tickets\\\",      \\\"Buy an Exclusive Experience\\\",      \\\"Take Advantage of This Offer\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Mastercard Reserve VIP\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014702" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014791," +
  				"            \"id\": \"b085b4d135fd4a73937538ae2b1c9882\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Notify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Notify Purchase - US\\\",    \\\"extra\\\": \\\"Purchase Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"notify\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 16,        \\\"class\\\": 1      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-slider-slide__footer\\\"        ],        \\\"tag\\\": \\\"footer\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"updated\\\": false,    \\\"targets\\\": [      \\\"{\\\\\\\"type\\\\\\\":\\\\\\\"class\\\\\\\",\\\\\\\"attributes\\\\\\\":{\\\\\\\"href\\\\\\\":\\\\\\\"/en-US/buy-tickets\\\\\\\",\\\\\\\"class\\\\\\\":[\\\\\\\"s-slider-slide__button\\\\\\\"],\\\\\\\"target\\\\\\\":\\\\\\\"_self\\\\\\\",\\\\\\\"data-track-element\\\\\\\":\\\\\\\"CTA\\\\\\\",\\\\\\\"data-track-slide-position\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"data-track-title\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"a\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy tickets\\\\\\\"},\\\\\\\"counts\\\\\\\":{\\\\\\\"tag\\\\\\\":16,\\\\\\\"class\\\\\\\":1},\\\\\\\"parent\\\\\\\":{\\\\\\\"class\\\\\\\":[\\\\\\\"s-slider-slide__footer\\\\\\\"],\\\\\\\"tag\\\\\\\":\\\\\\\"footer\\\\\\\",\\\\\\\"innertext\\\\\\\":\\\\\\\"buy tickets\\\\\\\"}}\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Notify Purchase - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014791" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014679," +
  				"            \"id\": \"359bd34b276940ce9b32a537761e2998\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Image Reserve Combination iPad\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"class\\\": [          \\\"s-promo__wrapper\\\"        ],        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"combination admissio\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 184,        \\\"class\\\": 13      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-promo\\\"        ],        \\\"data-reactroot\\\": \\\"\\\",        \\\"tag\\\": \\\"div\\\",        \\\"innertext\\\": \\\"combination admissio\\\"      }    },    \\\"parameter\\\": 6,    \\\"updated\\\": false,    \\\"styles\\\": [      \\\"//s3.us-east-2.amazonaws.com/elasticbeanstalk-us-east-2-owo-001/OWO_PPT-Selects/SUPPORT/2Lye8-mA.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/eMSa8EEg.jpeg\\\",      \\\"//elasticbeanstalk-us-east-2-owo-001.s3.us-east-2.amazonaws.com/OWO_PPT-Selects/SUPPORT/HyToOzR9.jpeg\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Image Reserve Combination iPad\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014679" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014771," +
  				"            \"id\": \"201c9ae9f45641ae9a6b5d79a7cc8dba\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Welcome All-Inclusive Tickets\\\",    \\\"extra\\\": \\\"Reserve All-Inclusive\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"reserve all-inclusiv\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 16      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/tickets?keyword=ALL-INCLUSIVE%20EXPERIENCE\\\",        \\\"class\\\": [          \\\"s-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"reserve all-inclusiv\\\"      }    },    \\\"parameter\\\": 9,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Reserve All-Inclusive\\\",      \\\"All-Inclusive Tickets\\\",      \\\"All-Inclusive Visit\\\",      \\\"Purchase the Ultimate Experience\\\",      \\\"Buy All-Inclusive Tickets\\\",      \\\"Experience All We Have to Offer\\\",      \\\"Experience Everything\\\",      \\\"All-Inclusive Packages\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Welcome All-Inclusive Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014771" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014854," +
  				"            \"id\": \"6aba2ebe0b494baeaaa8e1e567d24327\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Font color modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {\\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Text Color - US\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"fontcolor\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"comments\\\": \\\"\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 11      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\"      }    },    \\\"parameter\\\": 20,    \\\"updated\\\": false,    \\\"colors\\\": [      \\\"#1a28ea\\\",      \\\"#f50f0f\\\",      \\\"#f5c2c2\\\"    ]  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Text Color - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014854" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014938," +
  				"            \"id\": \"3c583f43497a496694ade27bd87942a7\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"type\\\": \\\"lists\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"countLists\\\": 0,    \\\"created\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"lastmodified\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014938" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280015035," +
  				"            \"id\": \"2e8393feecbf41688f1892bd8c845df3\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Site node\\\",  \\\"type\\\": \\\"site\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280015035" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014724," +
  				"            \"id\": \"6615304b553046a091a631b877d44546\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Raise Your Glass\\\",    \\\"extra\\\": \\\"Learn More\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"learn more\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 16      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/bar-restaurant\\\",        \\\"class\\\": [          \\\"s-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"learn more\\\"      }    },    \\\"parameter\\\": 8,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Learn More\\\",      \\\"Get Tickets for the Family\\\",      \\\"Tickets\\\",      \\\"An Unforgettable Perspective\\\",      \\\"A View Worth the Price of Admission\\\",      \\\"Witness the Grandeur\\\",      \\\"See for Yourself\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Raise Your Glass\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014724" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014812," +
  				"            \"id\": \"732134db32e44996a3e0f9e6c959c99d\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Style modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Style Color - US\\\",    \\\"extra\\\": \\\"background-image\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"style\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"class\\\",      \\\"attributes\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 16,        \\\"class\\\": 1      },      \\\"parent\\\": {        \\\"class\\\": [          \\\"s-slider-slide__footer\\\"        ],        \\\"tag\\\": \\\"footer\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 10,    \\\"updated\\\": true,    \\\"styles\\\": [      \\\"linear-gradient(to left,#f05b83,#003f6c)\\\",      \\\"linear-gradient(to left,#fbd6ac,#f98404)\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Style Color - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014812" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014894," +
  				"            \"id\": \"3be9b50a67b9463186e659242d5cd0b5\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Top Line Tickets - US\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 11      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-navigation__menu-link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 11,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Visit Options\\\",      \\\"Ticket Options\\\",      \\\"Buy Options\\\",      \\\"Purchase Now\\\",      \\\"Reserve Now\\\",      \\\"Buy Now\\\",      \\\"Purchase Tickets\\\",      \\\"Reserve Tickets\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Top Line Tickets - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014894" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014996," +
  				"            \"id\": \"06c12b2b8a80450e94aa90fe9fdbd921\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"type\\\": \\\"rules\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"lastmodified\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014996" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280015080," +
  				"            \"id\": \"db3def305c9147efb3e72e3d8fc776e4\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Text - US\\\",    \\\"extra\\\": \\\"Buy Tickets\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"Buy Tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 11      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"Buy Tickets\\\"      }    },    \\\"parameter\\\": 17,    \\\"updated\\\": true,    \\\"newtexts\\\": [      \\\"BUY TICKETS\\\",      \\\"Visit Options\\\",      \\\"Ticket Options\\\",      \\\"Buy Options\\\",      \\\"Purchase Now\\\",      \\\"Reserve Now\\\",      \\\"Buy Now\\\",      \\\"Purchase Tickets\\\",      \\\"Reserve Tickets\\\",      \\\"Buy Tickets\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Text - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280015080" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014751," +
  				"            \"id\": \"7fba2226dd6543858d26510a713038d8\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Checked type modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Experience Text Reserve Combination Tickets\\\",    \\\"extra\\\": \\\"Reserve Combination\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"/en-US/experience\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"reserve combination\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 18      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/tickets?package_id=2642\\\",        \\\"class\\\": [          \\\"s-call-to-action__link\\\"        ],        \\\"target\\\": \\\"_blank\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"reserve combination\\\"      }    },    \\\"parameter\\\": 7,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Reserve Combination\\\",      \\\"Combination Tickets\\\",      \\\"Buy an Upgraded Experience\\\",      \\\"Purchase a Combination Ticket\\\",      \\\"Buy a Combination Ticket\\\",      \\\"Combo Tickets\\\",      \\\"More Than Just the View\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Experience Text Reserve Combination Tickets\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014751" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014833," +
  				"            \"id\": \"eed882745613447297dd3c3af9d8debc\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Bottom Text - US\\\",    \\\"extra\\\": \\\"B\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 11      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/buy-tickets\\\",        \\\"class\\\": [          \\\"s-slider-slide__button\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"data-track-element\\\": \\\"CTA\\\",        \\\"data-track-slide-position\\\": \\\"1\\\",        \\\"data-track-title\\\": \\\"\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"buy tickets\\\"      }    },    \\\"parameter\\\": 11,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Buy Tickets\\\",      \\\"Visit Options\\\",      \\\"Ticket Options\\\",      \\\"Buy Options\\\",      \\\"Purchase Now\\\",      \\\"Reserve Now\\\",      \\\"Buy Now\\\",      \\\"Purchase Tickets\\\",      \\\"Reserve Tickets\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Bottom Text - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014833" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014914," +
  				"            \"id\": \"6e0aa19595ad49228c83653d1031c86b\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"type\\\": \\\"company\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"www.owo.dnsalias.com\\\",    \\\"created\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"lastmodified\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"passThru\\\": false,    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014914" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280015016," +
  				"            \"id\": \"f117df6619ab4851aa3366258d50a96c\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Division node\\\",  \\\"type\\\": \\\"division\\\",  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280015016" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280015100," +
  				"            \"id\": \"d22c7dbdab11477e87ed3fb3d24558ec\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2020-12-14T23:24:37.408Z\\\",    \\\"lastmodified\\\": \\\"2020-12-14T23:24:37.408Z\\\",    \\\"countReports\\\": 0,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"reports\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280015100" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014874," +
  				"            \"id\": \"93095ac8afee42a2910a486dc4dbf39e\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Text modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Home Top Line Experience - US\\\",    \\\"extra\\\": \\\"Experience\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"textchecked\\\",    \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\",    \\\"cssselector\\\": \\\"\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": {      \\\"type\\\": \\\"tag\\\",      \\\"attributes\\\": {        \\\"tag\\\": \\\"span\\\",        \\\"innertext\\\": \\\"experience\\\"      },      \\\"counts\\\": {        \\\"tag\\\": 11      },      \\\"parent\\\": {        \\\"href\\\": \\\"/en-US/experience\\\",        \\\"class\\\": [          \\\"s-navigation__menu-link\\\"        ],        \\\"target\\\": \\\"_self\\\",        \\\"tag\\\": \\\"a\\\",        \\\"innertext\\\": \\\"experience\\\"      }    },    \\\"parameter\\\": 12,    \\\"updated\\\": false,    \\\"newtexts\\\": [      \\\"Experience It All\\\",      \\\"Experience The Awe\\\",      \\\"Experience The View\\\",      \\\"Experiences\\\"    ],    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.oneworldobservatory.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Home Top Line Experience - US\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014874" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280014969," +
  				"            \"id\": \"17f8fcf14ff340a4bec430d436116ede\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"type\\\": \\\"reports\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"countReports\\\": 0,    \\\"created\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"lastmodified\\\": \\\"2021-03-07T01:07:52.698Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280014969" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280015060," +
  				"            \"id\": \"1a8a87fe45d04f299dd29d7bfceffff1\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Modify modification\\\",  \\\"type\\\": \\\"mod\\\",  \\\"details\\\": {    \\\"pathre\\\": false,    \\\"prob\\\":100.0,    \\\"usemode\\\":\\\"prod\\\",    \\\"comments\\\": \\\"This rule has been disabled to enable the iFrame to work\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"OWO Buy Tickets Fix Iframe\\\",    \\\"extra\\\": \\\"fixiframesrc\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"modify\\\",    \\\"path\\\": \\\"//.*/\\\",    \\\"cssselector\\\": \\\"#override\\\",    \\\"xpath\\\": \\\"\\\",    \\\"find\\\": [ ],    \\\"nodeiden\\\": { },    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"www.owo.dnsalias.com\\\",    \\\"Rules\\\",    \\\"example.com\\\",    \\\"example.com\\\",    \\\"OWO Buy Tickets Fix Iframe\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280015060" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280006775," +
  				"            \"id\": \"7785b37184e14c2fa3b1211416a029ff\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Ignore-lists node\\\",  \\\"type\\\": \\\"lists\\\",  \\\"details\\\": {    \\\"type\\\": \\\"lists\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Ignore Lists\\\",    \\\"countLists\\\": 0,    \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"bskinz.com\\\",    \\\"Ignore Lists\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280006775" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280006630," +
  				"            \"id\": \"2727ff2209d048f3be04f29113ade356\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Top node of the node tree\\\",  \\\"type\\\": \\\"top\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2020-12-14T23:24:37.408Z\\\",    \\\"lastmodified\\\": \\\"2020-12-14T23:24:37.408Z\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Top\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"top\\\",    \\\"updated\\\": false,    \\\"passThru\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280006630" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280006680," +
  				"            \"id\": \"40e44945ce4c48a486f18dd9f9f08d0a\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Companies node\\\",  \\\"type\\\": \\\"companies\\\",  \\\"details\\\": {    \\\"created\\\": \\\"2020-12-14T23:24:37.408Z\\\",    \\\"lastmodified\\\": \\\"2021-01-04T18:12:28.757343100Z\\\",    \\\"countCompanies\\\": 8,    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Companies\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": false,    \\\"type\\\": \\\"companies\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280006680" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280006863," +
  				"            \"id\": \"cc5ebbd0cf4c44cf809aece57a7775da\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Reports node\\\",  \\\"type\\\": \\\"reports\\\",  \\\"details\\\": {    \\\"type\\\": \\\"reports\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Reports\\\",    \\\"countReports\\\": 0,    \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"bskinz.com\\\",    \\\"Reports\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280006863" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"pass_javaa\"," +
  				"            \"created\": 1631280006936," +
  				"            \"id\": \"6e420174dcd64c659bb82220b1e57ec9\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Rules node\\\",  \\\"type\\\": \\\"rules\\\",  \\\"details\\\": {    \\\"type\\\": \\\"rules\\\",    \\\"created\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"lastmodified\\\":\\\"2022-11-18T02:04:30.296Z\\\",\\\"name\\\": \\\"Rules\\\",    \\\"countDivisions\\\": 1,    \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\",    \\\"updated\\\": false  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"Companies\\\",    \\\"bskinz.com\\\",    \\\"Rules\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1631280006936" +
  				"        }" +
  				"    ]," +
  				"    \"retcode\": 1" +
  				"}";
  /* This is the test data used to test (with Junit5) HDLmTree. This test data is
     fixed so that the tests yield reproducible results. The test data was obtained
     from a live system, so that it is quite complex and useful. */
  protected static String   jsonGetPassStr = jsonGetPassStrPart1 + jsonGetPassStrPart2 + jsonGetPassStrPart3;
  /* This is the test data used to test (with Junit5) HDLmTree. This test data is
     fixed so that the tests yield reproducible results. The test data was obtained
     from a live system, so that it is quite complex and useful. */
	protected static String   jsonGetPassStrSaved1 =
			"{" +
					"    \"rows_returned\": 1," +
					"    \"comments\": \"io for test_2\"," +
					"    \"data\": [" +
					"        {" +
					"            \"company\": \"example.com\"," +
					"            \"content\": \"pass_javaa\"," +
					"            \"created\": 1607811447054," +
					"            \"division\": \"example.com\"," +
					"            \"enabled\": true," +
					"            \"id\": \"8c3054710e504c7bbacad6c5474c2c44\"," +
					"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
					"            \"meta_classification\": \"\"," +
					"            \"meta_datatag\": \"[]\"," +
					"            \"meta_schema\": \"\"," +
					"            \"mods\": \"[" +
					"  {" +
					"  " +
					"    " +
					"    \\\"details\\\": {" +
					"      \\\"created\\\": \\\"2020-12-14T23:24:37.408Z\\\"," +
					"      \\\"lastmodified\\\": \\\"2020-12-14T23:24:37.408Z\\\"," +
					"      \\\"name\\\": \\\"Top\\\"," +
					"      \\\"extra\\\": \\\"\\\"," +
					"      \\\"enabled\\\": false," +
					"      \\\"type\\\": \\\"top\\\"," +
					"      \\\"updated\\\": false," +
					"      \\\"passThru\\\": false" +
					"    }," +
					"    \\\"tooltip\\\": \\\"Top node of the node tree\\\"," +
					"    \\\"type\\\": \\\"top\\\"," +
					"    \\\"children\\\": [" +
					"      {" +
					"      " +
					"        " +
					"        \\\"details\\\": {" +
					"          \\\"created\\\": \\\"2020-12-14T23:24:37.408Z\\\"," +
					"          \\\"lastmodified\\\": \\\"2020-12-14T23:24:37.408Z\\\"," +
					"          \\\"countCompanies\\\": 0," +
					"          \\\"name\\\": \\\"Companies\\\"," +
					"          \\\"extra\\\": \\\"\\\"," +
					"          \\\"enabled\\\": false," +
					"          \\\"type\\\": \\\"companies\\\"," +
					"          \\\"updated\\\": false" +
					"        }," +
					"        \\\"tooltip\\\": \\\"Companies node\\\"," +
					"        \\\"type\\\": \\\"companies\\\"," +
					"        \\\"children\\\": [" +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"legends-magento.dnsalias.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                    " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Title modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Add to Cart Text\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"button\\\"," +
					"                                  \\\"attribute\\\": \\\"id\\\"," +
					"                                  \\\"value\\\": \\\"product-addtocart-button\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 9," +
					"                              \\\"type\\\": \\\"title\\\"," +
					"                              \\\"titles\\\": [" +
					"                                \\\"ADD TO CART\\\"," +
					"                                \\\"Add to ala-carte\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Add to Cart Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Font color modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Font Color\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"span\\\"," +
					"                                  \\\"attribute\\\": \\\"itemprop\\\"," +
					"                                  \\\"value\\\": \\\"name\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 1," +
					"                              \\\"type\\\": \\\"fontcolor\\\"," +
					"                              \\\"colors\\\": [" +
					"                                \\\"#000000\\\"," +
					"                                \\\"#0000ff\\\"," +
					"                                \\\"#00ff00\\\"," +
					"                                \\\"#ff0000\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Font Color\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Font family modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Font Family\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"span\\\"," +
					"                                  \\\"attribute\\\": \\\"itemprop\\\"," +
					"                                  \\\"value\\\": \\\"name\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"type\\\": \\\"fontfamily\\\"," +
					"                              \\\"families\\\": [" +
					"                                \\\"arial\\\"," +
					"                                \\\"arial\\\"," +
					"                                \\\"cursive\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Font Family\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Font kerning modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Font Kerning\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [" +
					"                               {" +
					"                                  \\\"tag\\\": \\\"span\\\"," +
					"                                  \\\"attribute\\\": \\\"itemprop\\\"," +
					"                                  \\\"value\\\": \\\"name\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 4," +
					"                              \\\"type\\\": \\\"fontkerning\\\"," +
					"                              \\\"kernings\\\": [" +
					"                                \\\"auto\\\"," +
					"                                \\\"TCELESECROFnone\\\"," +
					"                                \\\"normal\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Font Kerning\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Font size modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Font Size\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"fontsize\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                             \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"span\\\"," +
					"                                  \\\"attribute\\\": \\\"itemprop\\\"," +
					"                                  \\\"value\\\": \\\"name\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"parameter\\\": 0," +
					"                              \\\"updated\\\": false," +
					"                              \\\"sizes\\\": [" +
					"                                \\\"100px\\\"," +
					"                                \\\"TCELESECROF20px\\\"," +
					"                                \\\"30\\\"," +
					"                                \\\"40px\\\"," +
					"                                \\\"50px\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Font Size\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Font style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Font Style\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"span\\\"," +
					"                                  \\\"attribute\\\": \\\"itemprop\\\"," +
					"                                  \\\"value\\\": \\\"name\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 5," +
					"                              \\\"type\\\": \\\"fontstyle\\\"," +
					"                              \\\"styles\\\": [" +
					"                                \\\"italic\\\"," +
					"                                \\\"normal\\\"," +
					"                                \\\"oblique\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Font Style\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Font Text\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"text\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Very Cuter PHP Proxy B\\\"," +
					"                                \\\"Very Cute\\\"," +
					"                                \\\"Not so cute\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/main/div[2]/div[1]/div[1]/div[1]/h1/span\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Font Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Font weight modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Font Weight\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"span\\\"," +
					"                                  \\\"attribute\\\": \\\"itemprop\\\"," +
					"                                  \\\"value\\\": \\\"name\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 6," +
					"                              \\\"type\\\": \\\"fontweight\\\"," +
					"                              \\\"weights\\\": [" +
					"                                \\\"normal\\\"," +
					"                                \\\"bold\\\"," +
					"                                \\\"lighter\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Font Weight\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Height modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Logo Height\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"height\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                             \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"a\\\"," +
					"                                  \\\"attribute\\\": \\\"class\\\"," +
					"                                  \\\"value\\\": \\\"logo\\\"" +
					"                                }," +
					"                                {" +
					"                                  \\\"tag\\\": \\\"img\\\"," +
					"                                  \\\"attribute\\\": \\\"\\\"," +
					"                                  \\\"value\\\": \\\"\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"parameter\\\": 8," +
					"                              \\\"updated\\\": false," +
					"                              \\\"heights\\\": [" +
					"                                \\\"145px\\\"," +
					"                                \\\"45px\\\"," +
					"                                \\\"50%\\\"," +
					"                                \\\"TCELESECROFauto\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Logo Height\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Width modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Logo Width\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                             \\\"type\\\": \\\"width\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"a\\\"," +
					"                                  \\\"attribute\\\": \\\"class\\\"," +
					"                                  \\\"value\\\": \\\"logo\\\"" +
					"                                }," +
					"                                {" +
					"                                  \\\"tag\\\": \\\"img\\\"," +
					"                                  \\\"attribute\\\": \\\"\\\"," +
					"                                  \\\"value\\\": \\\"\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"parameter\\\": 7," +
					"                              \\\"updated\\\": false," +
					"                              \\\"widths\\\": [" +
					"                                \\\"TCELESECROF250px\\\"," +
					"                                \\\"450px\\\"," +
					"                                \\\"auto\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Logo Width\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Notify\\\"," +
					"                              \\\"path\\\": \\\"/neve-studio-dance-jacket.html\\\"," +
					"                              \\\"find\\\": [" +
					"                                {" +
					"                                  \\\"tag\\\": \\\"form\\\"," +
					"                                  \\\"attribute\\\": \\\"id\\\"," +
					"                                  \\\"value\\\": \\\"product_addtocart_form\\\"" +
					"                                }" +
					"                              ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-magento.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Notify\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"legends-magento.dnsalias.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"legends-magento.dnsalias.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"legends-magento.dnsalias.com\\\"," +
					"                  \\\"Rules\\\"" +
					"                ]" +
					"              }" +
					"            ]," +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"legends-magento.dnsalias.com\\\"" +
					"            ]" +
					"          }," +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"legends-owo-secure.dnsalias.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                    " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE $0 - IT\\\"," +
					"                              \\\"extra\\\": \\\"$0.00\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[4]/div/div/div[2]/div/div[1]/div[1]/span[1]\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"0.00$\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE $0 - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE $33 - IT\\\"," +
					"                              \\\"extra\\\": \\\"$55.00\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[1]/div/div/div[2]/div/div[1]/div[1]/span[4]\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"55.00$\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE $33 - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE $49 - IT\\\"," +
					"                              \\\"extra\\\": \\\"$49.00\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[3]/div/div/div[2]/div/div[1]/div[1]/span[4]\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"49.00$\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE $49 - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE $55 - IT\\\"," +
					"                              \\\"extra\\\": \\\"$33.00\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/span[4]\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"33.00$\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE $55 - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE ADULT - IT\\\"," +
					"                              \\\"extra\\\": \\\"ADULT INCLUSIVE (13-64)\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[1]/div/div/div[1]/div/h1\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"INCLUSIVO ADULTO (13-64)\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE ADULT - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE CHILD - IT\\\"," +
					"                              \\\"extra\\\": \\\"CHILD INCLUSIVE (5 and Under)\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[4]/div/div/div[1]/div/h1\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"INCLUSIVE BAMBINI (5 e meno)\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE CHILD - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                           " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE SENIOR -IT\\\"," +
					"                              \\\"extra\\\": \\\"SENIOR INCLUSIVE (65+)\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[2]/div/div/div[1]/div/h1\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"SENIOR INCLUSIVE (65+)\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE SENIOR -IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO ALL-INCLUSIVE YOUTH - IT\\\"," +
					"                              \\\"extra\\\": \\\"YOUTH INCLUSIVE (6-12)\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/packageDetails/2577*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[1]/div[2]/ng-form/div[4]/div/div[3]/div/div/div[1]/div/h1\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"GIOVANI INCLUSIVE (6-12)\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO ALL-INCLUSIVE YOUTH - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO All Inclusive Evening Buy Now\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[2]/div[5]/div[1]/div[2]/ul/li[2]/div/div[4]/button\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"All Inclusive Evening Buy Now\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO All Inclusive Evening Buy Now\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO All Inclusive Morning Buy Now\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div[4]/div/div/div[2]/div[5]/div[1]/div[2]/ul/li[1]/div/div[4]/button\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"All Inclusive Morning Buy Now\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO All Inclusive Morning Buy Now\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Change 7.62\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"7.62\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"$7.00\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Change 7.62\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Complete Your Order\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"complete-order-btn\\\\\\\"]\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [" +
					"                                \\\"span.subtotal-total\\\"" +
					"                              ]," +
					"                              \\\"extra\\\": \\\"Complete Your Order\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Complete Your Order\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Extract modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Get Transaction ID\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"extract\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"cssselector\\\": \\\"span.order-id\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Get Transaction ID\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"legends-owo-secure.dnsalias.com\\\"," +
					"                  \\\"Rules\\\"" +
					"                ]" +
					"              }" +
					"            ]," +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"legends-owo-secure.dnsalias.com\\\"" +
					"            ]" +
					"          }," +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"legends-owo.dnsalias.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                   " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Image Background Test\\\"," +
					"                              \\\"extra\\\": \\\"background-image\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"path\\\": \\\"/en-US/*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 4," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Essentials-934396096.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Essentials-934396096.jpg\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Image Background Test\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $35 Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Now\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[3]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                           }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $35 Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $35 Top Line Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Tickets\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"a.is-active\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $35 Top Line Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $45 Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Now\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[2]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $45 Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Attribute modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $55 Fix Href\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"href/useproxyhost\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"attribute\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $55 Fix Href\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $55 Purchase\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"Buy $55\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $55 Purchase\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Experience\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"NYC Inspired Cuisine\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"NYC Cusine for Oscar and Mike\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Experience\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Order modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Order\\\"," +
					"                              \\\"path\\\": \\\"/en-US/*\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#ticket-package > div > div\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"order\\\"," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                             \\\"orders\\\": [" +
					"                                \\\"0,3,2,1,4,5,6\\\"," +
					"                                \\\"0,3,2,1,4,5,6\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Order\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Order modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Order - IT\\\"," +
					"                              \\\"path\\\": \\\"/it-IT/acquista-biglietti\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#ticket-package > div > div\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"order\\\"," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                              \\\"orders\\\": [" +
					"                                \\\"0,3,2,1,4,5,6\\\"," +
					"                                \\\"0,3,2,1,4,5,6\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Order - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Modify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Fix Iframe\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"fixiframesrc\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#override\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"modify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Fix Iframe\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Free Cheesecake\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"Stunning Views\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"#id-116 + span\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Free Cheesecake\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Free Cheesecake\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Remove Mastercard\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"Mastercard\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"A Guided Experience\\\"," +
					"                                \\\"A Guided Experience\\\"," +
					"                                \\\"A Guided Experience\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Remove Mastercard\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Purchase - DE\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"Purchase Tickets In German for Chris Cole\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Purchase - DE\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Purchase - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [" +
					"                                \\\".s-slide-call-to-action__link\\\"" +
					"                              ]," +
					"                              \\\"extra\\\": \\\"Purchase Tickets\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Purchase - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Style Color - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"extra\\\": \\\"background-color\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"linear-gradient(to left,#f05b83,#003f6c\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Style Color - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Style Width - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"extra\\\": \\\"width\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"100px\\\"," +
					"                                \\\"150px\\\"," +
					"                                \\\"200px\\\"," +
					"                                \\\"250px\\\"," +
					"                                \\\"300px\\\"," +
					"                                \\\"350px\\\"," +
					"                                \\\"400px\\\"," +
					"                                \\\"450px\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Style Width - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Text - DE\\\"," +
					"                              \\\"extra\\\": \\\"TICKETS KAUFEN\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\"," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Tickets Kaugen 1\\\"," +
					"                                \\\"Tickets Kaugen 2\\\"," +
					"                                \\\"Tickets Kaugen 3\\\"," +
					"                                \\\"Tickets Kaugen 4\\\"," +
					"                                \\\"Tickets Kaugen 5\\\"," +
					"                                \\\"Tickets Kaugen 6\\\"," +
					"                                \\\"Tickets Kaugen 7\\\"," +
					"                                \\\"Tickets Kaugen 8\\\"," +
					"                                \\\"Tickets Kaugen 9\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Text - DE\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Text - US\\\"," +
					"                              \\\"extra\\\": \\\"BUY TICKETS\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Visit Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Buy Tickets\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Text - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Top Line Experience - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Experience\\\"," +
					"                                \\\"Experience\\\"," +
					"                                \\\"Experience\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"Experience\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Top Line Experience - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Top Line Tickets - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Visit Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Buy Tickets\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"Buy Tickets\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-owo.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Top Line Tickets - US\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"legends-owo.dnsalias.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"legends-owo.dnsalias.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"legends-owo.dnsalias.com\\\"," +
					"                  \\\"Rules\\\" " +
					"                ]" +
					"              }" +
					"            ]," +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"legends-owo.dnsalias.com\\\"" +
					"            ]" +
					"          }," +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"legends-test-secure.dnsalias.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                    " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Change 7.62\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"7.62\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"$7.00\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test-secure.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                             \\\"OWO Change 7.62\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"legends-test-secure.dnsalias.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"legends-test-secure.dnsalias.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"legends-test-secure.dnsalias.com\\\"," +
					"                  \\\"Rules\\\"" +
					"                ]" +
					"              }" +
					"            ],  " +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"legends-test-secure.dnsalias.com\\\"" +
					"            ]" +
					"          }," +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"legends-test.dnsalias.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                    " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Bottom Purchase\\\"," +
					"                              \\\"path\\\": \\\"/\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"Extra Bottom\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Bottom Purchase\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Bottom Text\\\"," +
					"                              \\\"path\\\": \\\"/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"text\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Now!!!\\\"," +
					"                                \\\"Buy Tickets Cheapest\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Bottom Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Change Experience\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"NYC Inspired Cuisine\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"NYC Cusine for Oscar and Mike\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Change Experience\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Order modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Change Order\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"#ticket-package > div > div\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"order\\\"," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                              \\\"orders\\\": [" +
					"                                \\\"0,3,2,1,4,5,6\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Change Order\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Attribute modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Fix Href\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"href/useproxyhost\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"attribute\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Fix Href\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Modify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Fix Iframe\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"fixiframesrc\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"#override\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"modify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Fix Iframe\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Free Cheesecake\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"#id-116 + span\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"text\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Free Cheesecake\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Free Cheesecake\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Remove Mastercard\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"Mastercard\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"A Guided Experience for Almost All\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Remove Mastercard\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Top Line Experience\\\"," +
					"                              \\\"path\\\": \\\"/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"text\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy From Chris\\\"," +
					"                                \\\"Buy From Mr. Tanner\\\"," +
					"                                \\\"Buy From Julian\\\"," +
					"                                \\\"Buy Now!!!\\\"," +
					"                                \\\"Top OWO Experience Now\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Top Line Experience\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Top Line Tickets\\\"," +
					"                              \\\"path\\\": \\\"/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"text\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Now!!!\\\"," +
					"                                \\\"Top OWO Tickets\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"legends-test.dnsalias.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Top Line Tickets\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"legends-test.dnsalias.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"legends-test.dnsalias.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"legends-test.dnsalias.com\\\"," +
					"                  \\\"Rules\\\" " +
					"                ]" +
					"              }" +
					"            ]," +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"legends-test.dnsalias.com\\\"" +
					"            ]" +
					"          }," +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"oneworldobservatory.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                    " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Image Background Test\\\"," +
					"                              \\\"extra\\\": \\\"background-image\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"path\\\": \\\"/en-US/*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 4," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"//a/b\\\"," +
					"                                \\\"TCELESECROF//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Essentials-934396096.jpg\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Image Background Test\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $35 Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Now\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[3]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $35 Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $35 Top Line Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Tickets\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"a.is-active\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $35 Top Line Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $45 Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Now\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[2]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $45 Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Attribute modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $55 Fix Href\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"href/useproxyhost\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"attribute\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $55 Fix Href\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $55 Purchase\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"Buy $55\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $55 Purchase\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Experience\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"NYC Inspired Cuisine\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"NYC Cusine for Oscar and Mike\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Experience\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Parameters\\\"," +
					"                              \\\"path1\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"path\\\": \\\"/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": null," +
					"                              \\\"extrb\\\": \\\"Buy $55\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Parameters\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Text\\\"," +
					"                              \\\"path1\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"path\\\": \\\"/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"text\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Very Cuter PHP Proxy B\\\"," +
					"                                \\\"Very Cute\\\"," +
					"                                \\\"Not so cute\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": null," +
					"                              \\\"extrb\\\": \\\"Buy $55\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Order modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Order\\\"," +
					"                              \\\"path\\\": \\\"/en-US/*\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#ticket-package > div > div\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"order\\\"," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                              \\\"orders\\\": [" +
					"                                \\\"0,3,2,1,4,5,6\\\"," +
					"                                \\\"0,3,2,1,4,5,6\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Order\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Order modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Order - IT\\\"," +
					"                              \\\"path\\\": \\\"/it-IT/acquista-biglietti\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#ticket-package > div > div\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"order\\\"," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                              \\\"orders\\\": [" +
					"                                \\\"0,3,2,1,4,5,6\\\"," +
					"                                \\\"0,3,2,1,4,5,6\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Order - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Modify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Fix Iframe\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"fixiframesrc\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#override\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"modify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Fix Iframe\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Free Cheesecake\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"Stunning Views\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"#id-116 + span\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Free Cheesecake\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Free Cheesecake\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Remove Mastercard\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"Mastercard\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"A Guided Experience\\\"," +
					"                                \\\"A Guided Experience\\\"," +
					"                                \\\"A Guided Experience\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Remove Mastercard\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Purchase - DE\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"Purchase Tickets In German for Chris Cole\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Purchase - DE\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Purchase - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [" +
					"                                \\\".s-slide-call-to-action__link\\\"" +
					"                              ]," +
					"                              \\\"extra\\\": \\\"Purchase Tickets\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Purchase - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Style Color - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"extra\\\": \\\"background-color\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"linear-gradient(to left,#f05b83,#003f6c\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Style Color - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Style Width - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"extra\\\": \\\"width\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"100px\\\"," +
					"                                \\\"150px\\\"," +
					"                                \\\"200px\\\"," +
					"                                \\\"250px\\\"," +
					"                                \\\"300px\\\"," +
					"                                \\\"350px\\\"," +
					"                                \\\"400px\\\"," +
					"                                \\\"450px\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Style Width - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Text - DE\\\"," +
					"                              \\\"extra\\\": \\\"TICKETS KAUFEN\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\"," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Tickets Kaugen 1\\\"," +
					"                                \\\"Tickets Kaugen 2\\\"," +
					"                                \\\"Tickets Kaugen 3\\\"," +
					"                                \\\"Tickets Kaugen 4\\\"," +
					"                                \\\"Tickets Kaugen 5\\\"," +
					"                                \\\"Tickets Kaugen 6\\\"," +
					"                                \\\"Tickets Kaugen 7\\\"," +
					"                                \\\"Tickets Kaugen 8\\\"," +
					"                                \\\"Tickets Kaugen 9\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Text - DE\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Text - US\\\"," +
					"                              \\\"extra\\\": \\\"BUY TICKETS\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Visit Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Buy Tickets\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Text - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Top Line Experience - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Experience\\\"," +
					"                                \\\"Experience\\\"," +
					"                                \\\"Experience\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"Experience\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Top Line Experience - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Top Line Tickets\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Visit Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Buy Tickets\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"Buy Tickets\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Top Line Tickets\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Node Info Test 1\\\"," +
					"                              \\\"extra\\\": \\\"/en-US/*\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"nodeinfo\\\": {" +
					"                                \\\"type\\\": \\\"class\\\"," +
					"                                \\\"attributes\\\": {" +
					"                                  \\\"href\\\": \\\"/books-music-movies\\\"," +
					"                                  \\\"class\\\": [" +
					"                                    \\\"navMenuItem-link\\\"" +
					"                                  ]," +
					"                                  \\\"data-reactid\\\": \\\"162\\\"," +
					"                                  \\\"tag\\\": \\\"a\\\"" +
					"                                }," +
					"                                \\\"counts\\\": {" +
					"                                  \\\"tag\\\": 71," +
					"                                  \\\"class\\\": 7" +
					"                                }," +
					"                                \\\"parent\\\": {" +
					"                                  \\\"class\\\": [" +
					"                                    \\\"navMenuItem\\\"" +
					"                                  ]," +
					"                                  \\\"data-reactid\\\": \\\"161\\\"," +
					"                                  \\\"tag\\\": \\\"li\\\"" +
					"                                }" +
					"                              }," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Test 1\\\"" +
					"                              ]" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Node Info Test 1\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"oneworldobservatory.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"oneworldobservatory.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"oneworldobservatory.com\\\"," +
					"                  \\\"Rules\\\"" +
					"                ]" +
					"              }" +
					"            ]," +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"oneworldobservatory.com\\\"" +
					"            ]" +
					"          }," +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"shop.oneworldobservatory.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                    " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Image modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Image Test\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"type\\\": \\\"image\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"/html/body/div[1]/div[1]/div/ul[2]/li[4]/article/figure/a/div/img\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                              \\\"images\\\": [" +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Essentials-934396096.jpg\\\"," +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Essentials-934396096.jpg\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"shop.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Image Test\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"shop.oneworldobservatory.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"shop.oneworldobservatory.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"shop.oneworldobservatory.com\\\"," +
					"                  \\\"Rules\\\" " +
					"                ]" +
					"              }" +
					"            ]," +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"shop.oneworldobservatory.com\\\"" +
					"            ]" +
					"          }," +
					"          {" +
					"          " +
					"            " +
					"            \\\"details\\\": {" +
					"              \\\"type\\\": \\\"company\\\"," +
					"              \\\"name\\\": \\\"www.oneworldobservatory.com\\\"," +
					"              \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"              \\\"passThru\\\": false," +
					"              \\\"updated\\\": false" +
					"            }," +
					"            \\\"tooltip\\\": \\\"Company node\\\"," +
					"            \\\"type\\\": \\\"company\\\"," +
					"            \\\"children\\\": [" +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Ignore-lists node\\\"," +
					"                \\\"type\\\": \\\"lists\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"lists\\\"," +
					"                  \\\"name\\\": \\\"Ignore Lists\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Ignore Lists\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"tooltip\\\": \\\"Reports node\\\"," +
					"                \\\"type\\\": \\\"reports\\\"," +
					"                \\\"children\\\": [ ]," +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"reports\\\"," +
					"                  \\\"name\\\": \\\"Reports\\\"," +
					"                  \\\"countReports\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"bskinz.com\\\"," +
					"                  \\\"Reports\\\"" +
					"                ]" +
					"              }," +
					"              {" +
					"              " +
					"                " +
					"                \\\"details\\\": {" +
					"                  \\\"type\\\": \\\"rules\\\"," +
					"                  \\\"name\\\": \\\"Rules\\\"," +
					"                  \\\"countLists\\\": 0," +
					"                  \\\"created\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"lastmodified\\\": \\\"2021-01-05T21:42:38.150Z\\\"," +
					"                  \\\"updated\\\": false" +
					"                }," +
					"                \\\"tooltip\\\": \\\"Rules node\\\"," +
					"                \\\"type\\\": \\\"rules\\\"," +
					"                \\\"children\\\": [" +
					"                  {" +
					"                  " +
					"                    " +
					"                    \\\"tooltip\\\": \\\"Division node\\\"," +
					"                    \\\"type\\\": \\\"division\\\"," +
					"                    \\\"children\\\": [" +
					"                      {" +
					"                      " +
					"                        " +
					"                        \\\"tooltip\\\": \\\"Site node\\\"," +
					"                        \\\"type\\\": \\\"site\\\"," +
					"                        \\\"children\\\": [" +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"Image Background Test\\\"," +
					"                              \\\"extra\\\": \\\"background-image\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"path\\\": \\\"/en-US/*\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 4," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"//www.istockphoto.com/resources/images/PhotoFTLP/Signature-1149137912_1.jpg\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"Image Background Test\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $35 Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Now\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[3]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $35 Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $35 Top Line Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Tickets\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"a.is-active\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $35 Top Line Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $45 Text\\\"," +
					"                              \\\"extra\\\": \\\"Buy Now\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[2]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Visit Options\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $45 Text\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Attribute modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $55 Fix Href\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"href/useproxyhost\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"attribute\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $55 Fix Href\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets $55 Purchase\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"//*[@id=\\\\\\\"ticket-package\\\\\\\"]/div/div/article[1]/header/div[3]/div/div/a\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"Buy $55\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets $55 Purchase\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Experience\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"NYC Inspired Cuisine\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"NYC Cusine for Oscar and Mike\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Experience\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Order modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Order\\\"," +
					"                              \\\"path\\\": \\\"/en-US/*\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#ticket-package > div > div\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"order\\\"," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                              \\\"orders\\\": [" +
					"                                \\\"0,3,2,1,4,5,6\\\"," +
					"                                \\\"0,3,2,1,4,5,6\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Order\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Order modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Change Order - IT\\\"," +
					"                              \\\"path\\\": \\\"/it-IT/acquista-biglietti\\\"," +
					"                              \\\"extra\\\": \\\"\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#ticket-package > div > div\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"order\\\"," +
					"                              \\\"parameter\\\": 3," +
					"                              \\\"updated\\\": false," +
					"                              \\\"orders\\\": [" +
					"                                \\\"0,3,2,1,4,5,6\\\"," +
					"                                \\\"0,3,2,1,4,5,6\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Change Order - IT\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Modify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Fix Iframe\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"fixiframesrc\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\"#override\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"modify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Fix Iframe\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Free Cheesecake\\\"," +
					"                              \\\"path\\\": \\\"/en-US/buy-tickets\\\"," +
					"                              \\\"extra\\\": \\\"Stunning Views\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"#id-116 + span\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Free Cheesecake\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Free Cheesecake\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Buy Tickets Remove Mastercard\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"extra\\\": \\\"Mastercard\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\"h3.s-ticket-package-description__name\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"A Guided Experience\\\"," +
					"                                \\\"A Guided Experience\\\"," +
					"                                \\\"A Guided Experience\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Buy Tickets Remove Mastercard\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Purchase - DE\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [ ]," +
					"                              \\\"extra\\\": \\\"Purchase Tickets In German for Chris Cole\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Purchase - DE\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Notify modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Purchase - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"notify\\\"," +
					"                              \\\"updated\\\": false," +
					"                              \\\"targets\\\": [" +
					"                                \\\".s-slide-call-to-action__link\\\"" +
					"                              ]," +
					"                              \\\"extra\\\": \\\"Purchase Tickets\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Purchase - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Style Color - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"extra\\\": \\\"background-color\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"linear-gradient(to left,#f05b83,#003f6c\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Style Color - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Style modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Style Width - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"extra\\\": \\\"width\\\"," +
					"                              \\\"enabled\\\": false," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"type\\\": \\\"style\\\"," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"styles\\\": [" +
					"                                \\\"100px\\\"," +
					"                                \\\"150px\\\"," +
					"                                \\\"200px\\\"," +
					"                                \\\"250px\\\"," +
					"                                \\\"300px\\\"," +
					"                                \\\"350px\\\"," +
					"                                \\\"400px\\\"," +
					"                                \\\"450px\\\"" +
					"                              ]," +
					"                             \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Style Width - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Text - DE\\\"," +
					"                              \\\"extra\\\": \\\"TICKETS KAUFEN\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/de-DE(\\\\\\\\/)?/\\\"," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Tickets Kaugen 1\\\"," +
					"                                \\\"Tickets Kaugen 2\\\"," +
					"                                \\\"Tickets Kaugen 3\\\"," +
					"                                \\\"Tickets Kaugen 4\\\"," +
					"                                \\\"Tickets Kaugen 5\\\"," +
					"                                \\\"Tickets Kaugen 6\\\"," +
					"                                \\\"Tickets Kaugen 7\\\"," +
					"                                \\\"Tickets Kaugen 8\\\"," +
					"                                \\\"Tickets Kaugen 9\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Text - DE\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Bottom Text - US\\\"," +
					"                              \\\"extra\\\": \\\"BUY TICKETS\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"cssselector\\\": \\\".s-slide-call-to-action__link\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Visit Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Buy Tickets\\\"" +
					"                              ]," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Bottom Text - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Top Line Experience - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Experience\\\"," +
					"                                \\\"Experience\\\"," +
					"                                \\\"Experience\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"Experience\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Top Line Experience - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Text modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Home Top Line Tickets - US\\\"," +
					"                              \\\"path\\\": \\\"//\\\\\\\\/en-US(\\\\\\\\/)?/\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Visit Options\\\"," +
					"                                \\\"Ticket Options\\\"," +
					"                                \\\"Buy Options\\\"," +
					"                                \\\"Purchase Now\\\"," +
					"                                \\\"Reserve Now\\\"," +
					"                                \\\"Buy Now\\\"," +
					"                                \\\"Purchase Tickets\\\"," +
					"                                \\\"Reserve Tickets\\\"," +
					"                                \\\"Buy Tickets\\\"" +
					"                              ]," +
					"                              \\\"updated\\\": false," +
					"                              \\\"xpath\\\": \\\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"extra\\\": \\\"Buy Tickets\\\"," +
					"                              \\\"nodeinfo\\\": \\\"\\\"" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Home Top Line Tickets - US\\\"" +
					"                            ]" +
					"                          }," +
					"                          {" +
					"                          " +
					"                            " +
					"                            \\\"tooltip\\\": \\\"Checked type modification\\\"," +
					"                            \\\"type\\\": \\\"mod\\\"," +
					"                            \\\"children\\\": [ ]," +
					"                            \\\"details\\\": {" +
					"                              \\\"name\\\": \\\"OWO Node Info Test 2\\\"," +
					"                              \\\"extra\\\": \\\"Buy Tickets\\\"," +
					"                              \\\"enabled\\\": true," +
					"                              \\\"type\\\": \\\"textchecked\\\"," +
					"                              \\\"path\\\": \\\"//.*/\\\"," +
					"                              \\\"cssselector\\\": \\\"\\\"," +
					"                              \\\"xpath\\\": \\\"\\\"," +
					"                              \\\"find\\\": [ ]," +
					"                              \\\"nodeinfo\\\": {" +
					"                                \\\"type\\\": \\\"class\\\"," +
					"                                \\\"attributes\\\": {" +
					"                                  \\\"href\\\": \\\"/books-music-movies\\\"," +
					"                                  \\\"class\\\": [" +
					"                                    \\\"navMenuItem-link\\\"" +
					"                                  ]," +
					"                                  \\\"data-reactid\\\": \\\"162\\\"," +
					"                                  \\\"tag\\\": \\\"a\\\"" +
					"                                }," +
					"                                \\\"counts\\\": {" +
					"                                  \\\"tag\\\": 71," +
					"                                  \\\"class\\\": 7" +
					"                                }," +
					"                                \\\"parent\\\": {" +
					"                                  \\\"class\\\": [" +
					"                                    \\\"navMenuItem\\\"" +
					"                                  ]," +
					"                                  \\\"data-reactid\\\": \\\"161\\\"," +
					"                                  \\\"tag\\\": \\\"li\\\"" +
					"                                }" +
					"                              }," +
					"                              \\\"parameter\\\": 2," +
					"                              \\\"updated\\\": false," +
					"                              \\\"newtexts\\\": [" +
					"                                \\\"Buy Test 2\\\"" +
					"                              ]" +
					"                            }," +
					"                            \\\"nodePath\\\": [" +
					"                              \\\"Top\\\"," +
					"                              \\\"Companies\\\"," +
					"                              \\\"www.oneworldobservatory.com\\\"," +
					"                              \\\"Rules\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"example.com\\\"," +
					"                              \\\"OWO Node Info Test 2\\\"" +
					"                            ]" +
					"                          }" +
					"                        ]," +
					"                        \\\"nodePath\\\": [" +
					"                          \\\"Top\\\"," +
					"                          \\\"Companies\\\"," +
					"                          \\\"www.oneworldobservatory.com\\\"," +
					"                          \\\"Rules\\\"," +
					"                          \\\"example.com\\\"," +
					"                          \\\"example.com\\\"" +
					"                        ]" +
					"                      }" +
					"                    ]," +
					"                    \\\"nodePath\\\": [" +
					"                      \\\"Top\\\"," +
					"                      \\\"Companies\\\"," +
					"                      \\\"www.oneworldobservatory.com\\\"," +
					"                      \\\"Rules\\\"," +
					"                      \\\"example.com\\\"" +
					"                    ]" +
					"                  }" +
					"                ]," +
					"                \\\"nodePath\\\": [" +
					"                  \\\"Top\\\"," +
					"                  \\\"Companies\\\"," +
					"                  \\\"www.oneworldobservatory.com\\\"," +
					"                  \\\"Rules\\\"" +
					"                ]" +
					"              }" +
					"            ]," +
					"            \\\"nodePath\\\": [" +
					"              \\\"Top\\\"," +
					"              \\\"Companies\\\"," +
					"              \\\"www.oneworldobservatory.com\\\"" +
					"            ]" +
					"          }" +
					"        ]," +
					"        \\\"nodePath\\\": [" +
					"          \\\"Top\\\"," +
					"          \\\"Companies\\\"" +
					"        ]" +
					"      }," +
					"      {" +
					"      " +
					"        " +
					"        \\\"details\\\": {" +
					"          \\\"created\\\": \\\"2020-12-14T23:24:37.408Z\\\"," +
					"          \\\"lastmodified\\\": \\\"2020-12-14T23:24:37.408Z\\\"," +
					"          \\\"countReports\\\": 0," +
					"          \\\"name\\\": \\\"Reports\\\"," +
					"          \\\"extra\\\": \\\"\\\"," +
					"          \\\"enabled\\\": false," +
					"          \\\"type\\\": \\\"reports\\\"," +
					"          \\\"updated\\\": false" +
					"        }," +
					"        \\\"tooltip\\\": \\\"Reports node\\\"," +
					"        \\\"type\\\": \\\"reports\\\"," +
					"        \\\"children\\\": [ ]," +
					"        \\\"nodePath\\\": [" +
					"          \\\"Top\\\"," +
					"          \\\"Reports\\\"" +
					"        ]" +
					"      }" +
					"    ]," +
					"    \\\"nodePath\\\": [" +
					"      \\\"Top\\\"" +
					"    ]" +
					"  }" +
					"]" +
					"\"," +
					"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
					"            \"ref_display\": \"\"," +
					"            \"relationships\": \"\"," +
					"            \"site\": \"example.com\"," +
					"            \"updated\": 1615470304581" +
					"        }" +
					"    ]," +
					"    \"retcode\": 1" +
					"}";
  /* This is the test data used to test (with Junit5) HDLmTree. This test data is
     fixed so that the tests yield reproducible results. The test data was obtained
     from a live system, so that it is quite complex and useful. */
  protected static final String  jsonGetProxyStr =
  		"{" +
  				"    \"rows_returned\": 8," +
  				"    \"comments\": \"io for main_2\"," +
  				"    \"data\": [" +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683690635," +
  				"            \"id\": \"f2da514050f94145a667dca8e44f4b91\"," +
  				"            \"info\": \"{\\\"tooltip\\\": \\\"Top node of the node tree\\\",  \\\"type\\\": \\\"top\\\",  \\\"nodePath\\\": [    \\\"Top\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683690635" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683691229," +
  				"            \"id\": \"c21acb14dbe04a93bcd55d2143cec1dd\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"name\\\": \\\"oneworldobservatory.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"match\\\": \\\"oneworldobservatory.com\\\",    \\\"type\\\": \\\"inject\\\",    \\\"backendType\\\": \\\"https\\\",    \\\"backendServer\\\": \\\"oneworldobservatory.com\\\",    \\\"secureServer\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"oneworldobservatory.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683691229" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683691821," +
  				"            \"id\": \"f749d482537a47a88c52c1986d5d6048\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"name\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"match\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"type\\\": \\\"inject\\\",    \\\"backendType\\\": \\\"https\\\",    \\\"backendServer\\\": \\\"owo.secure-cdn.na.accessoticketing.com\\\",    \\\"secureServer\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"owo.secure.dnsalias.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683691821" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683692403," +
  				"            \"id\": \"dfe44c1eb01a43ef87871bf74fc98271\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"name\\\": \\\"www.oneworldobservatory.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"match\\\": \\\"www.oneworldobservatory.com\\\",    \\\"type\\\": \\\"inject\\\",    \\\"backendType\\\": \\\"https\\\",    \\\"backendServer\\\": \\\"www.oneworldobservatory.com\\\",    \\\"secureServer\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"www.oneworldobservatory.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683692403" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683690942," +
  				"            \"id\": \"57cb8eefe3904ad3ab263554ca043b07\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"name\\\": \\\"bskinz.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"inject\\\",    \\\"backendType\\\": \\\"https\\\",    \\\"backendServer\\\": \\\"bskinz.com\\\",    \\\"match\\\": \\\"bskinz.com\\\",    \\\"secureServer\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"bskinz.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683690942" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683691530," +
  				"            \"id\": \"be377c20ea5f4b04984bb2f4565e1bef\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"name\\\": \\\"owo.dnsalias.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"match\\\": \\\"%owo.dnsalias.com\\\",    \\\"type\\\": \\\"inject\\\",    \\\"backendType\\\": \\\"https\\\",    \\\"backendServer\\\": \\\"www.oneworldobservatory.com\\\",    \\\"secureServer\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"owo.dnsalias.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683691530" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683692113," +
  				"            \"id\": \"3bb6ca6f58854150a91860dcddf4c767\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"name\\\": \\\"test.bskinz.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"type\\\": \\\"inject\\\",    \\\"backendType\\\": \\\"https\\\",    \\\"backendServer\\\": \\\"bskinz.com\\\",    \\\"match\\\": \\\"test.bskinz.com\\\",    \\\"secureServer\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"test.bskinz.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683692113" +
  				"        }," +
  				"        {" +
  				"            \"content\": \"proxy_javaa\"," +
  				"            \"created\": 1630683692689," +
  				"            \"id\": \"91c7e061d63e4f0c8c746b9b2045ac97\"," +
  				"            \"info\": \"{  \\\"tooltip\\\": \\\"Company node\\\",  \\\"type\\\": \\\"company\\\",  \\\"details\\\": {    \\\"name\\\": \\\"www.owo-web-uat.corebine.com\\\",    \\\"extra\\\": \\\"\\\",    \\\"enabled\\\": true,    \\\"match\\\": \\\"www.owo-web-uat.corebine.com\\\",    \\\"type\\\": \\\"inject\\\",    \\\"backendType\\\": \\\"https\\\",    \\\"backendServer\\\": \\\"www.oneworldobservatory.com\\\",    \\\"secureServer\\\": \\\"owo.secure.dnsalias.com\\\",    \\\"updated\\\": false,    \\\"comments\\\": \\\"\\\"  },  \\\"nodePath\\\": [    \\\"Top\\\",    \\\"www.owo-web-uat.corebine.com\\\"  ]}\"," +
  				"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
  				"            \"meta_classification\": \"\"," +
  				"            \"meta_datatag\": \"[]\"," +
  				"            \"meta_schema\": \"\"," +
  				"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
  				"            \"ref_display\": \"\"," +
  				"            \"relationships\": \"\"," +
  				"            \"updated\": 1630683692689" +
  				"        }" +
  				"    ]," +
  				"    \"retcode\": 1" +
  				"}";
  /* This is the test data used to test (with Junit5) HDLmTree. This test data is
     fixed so that the tests yield reproducible results. The test data was obtained
     from a live system, so that it is quite complex and useful. */
	protected static final String  jsonGetProxyStrSaved1 =
			"{" +
					"    \"rows_returned\": 1," +
					"    \"comments\": \"io for test_2\"," +
					"    \"data\": [" +
					"        {" +
					"            \"company\": \"example.com\"," +
					"            \"content\": \"proxy_javaa\"," +
					"            \"created\": 1568858842156," +
					"            \"division\": \"example.com\"," +
					"            \"enabled\": true," +
					"            \"id\": \"d3649fd2ba8a4ff8b8d7d685a55ad236\"," +
					"            \"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
					"            \"meta_classification\": \"\"," +
					"            \"meta_datatag\": \"[]\"," +
					"            \"meta_schema\": \"\"," +
					"            \"mods\": \"[{\\\"tooltip\\\":\\\"Top node of the node tree\\\",\\\"type\\\":\\\"top\\\",\\\"children\\\":" +
					"                [" +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"bskinz.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"bskinz.com\\\",\\\"match\\\":\\\"bskinz.com\\\",\\\"secureServer\\\":\\\"owo.secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"bskinz.com\\\"]}," +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"legends-owo.dnsalias.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"oneworldobservatory.com\\\",\\\"match\\\":\\\"bskinz.com\\\",\\\"secureServer\\\":\\\"legends-owo-secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\"]}," +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"oneworldobservatory.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"match\\\":\\\"oneworldobservatory.com\\\",\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"oneworldobservatory.com\\\",\\\"secureServer\\\":\\\"owo.secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\"]}," +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"owo.dnsalias.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"match\\\":\\\"%owo.dnsalias.com\\\",\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"www.oneworldobservatory.com\\\",\\\"secureServer\\\":\\\"owo.secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"owo.dnsalias.com\\\"]}," +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"owo.secure.dnsalias.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"match\\\":\\\"owo.secure.dnsalias.com\\\",\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"owo.secure-cdn.na.accessoticketing.com\\\",\\\"secureServer\\\":\\\"owo.secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"owo.secure.dnsalias.com\\\"]}," +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"test.bskinz.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"bskinz.com\\\",\\\"match\\\":\\\"test.bskinz.com\\\",\\\"secureServer\\\":\\\"owo.secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"test.bskinz.com\\\"]}," +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"www.oneworldobservatory.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"match\\\":\\\"www.oneworldobservatory.com\\\",\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"www.oneworldobservatory.com\\\",\\\"secureServer\\\":\\\"owo.secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"www.oneworldobservatory.com\\\"]}," +
					"                    {\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"www.owo-web-uat.corebine.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"match\\\":\\\"www.owo-web-uat.corebine.com\\\",\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"www.oneworldobservatory.com\\\",\\\"secureServer\\\":\\\"owo.secure.dnsalias.com\\\",\\\"updated\\\":false,\\\"comments\\\":\\\"\\\"},\\\"nodePath\\\":[\\\"Top\\\",\\\"www.owo-web-uat.corebine.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\"]}" +
					"                ]\"," +
					"            \"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
					"            \"ref_display\": \"\"," +
					"            \"relationships\": \"\"," +
					"            \"site\": \"example.com\"," +
					"            \"updated\": 1615141454437" +
					"        }" +
					"    ]," +
					"    \"retcode\": 1" +
					"}";
  /* This is the test data used to test (with Junit5) HDLmTree. This test data is
     fixed so that the tests yield reproducible results. The test data was obtained
     from a live system, so that it is quite complex and useful. */
protected static final String  jsonGetProxyStrSaved2 = "[\r\n" +
		"  {\r\n" +
		"    \\r\n" +
		"    \"tooltip\": \"Top node of the node tree\",\r\n" +
		"    \"type\": \"top\",\r\n" +
		"    \"children\": [\r\n" +
		"      {\r\n" +
		"        \\r\n" +
		"        \"tooltip\": \"Company node\",\r\n" +
		"        \"type\": \"company\",\r\n" +
		"        \"children\": [ ],\r\n" +
		"        \"details\": {\r\n" +
		"          \"name\": \"bskinz.com\",\r\n" +
		"          \"extra\": \"\",\r\n" +
		"          \"enabled\": true,\r\n" +
		"          \"type\": \"inject\",\r\n" +
		"          \"backendType\": \"https\",\r\n" +
		"          \"backendServer\": \"bskinz.com\",\r\n" +
		"          \"match\": \"bskinz.com\",\r\n" +
		"          \"secureServer\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"updated\": false,\r\n" +
		"          \"comments\": \"\"\r\n" +
		"        },\r\n" +
		"        \"nodePath\": [\r\n" +
		"          \"Top\",\r\n" +
		"          \"bskinz.com\"\r\n" +
		"        ]\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"        \\r\n" +
		"        \"tooltip\": \"Company node\",\r\n" +
		"        \"type\": \"company\",\r\n" +
		"        \"children\": [ ],\r\n" +
		"        \"details\": {\r\n" +
		"          \"name\": \"oneworldobservatory.com\",\r\n" +
		"          \"extra\": \"\",\r\n" +
		"          \"enabled\": true,\r\n" +
		"          \"match\": \"oneworldobservatory.com\",\r\n" +
		"          \"type\": \"inject\",\r\n" +
		"          \"backendType\": \"https\",\r\n" +
		"          \"backendServer\": \"oneworldobservatory.com\",\r\n" +
		"          \"secureServer\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"updated\": false,\r\n" +
		"          \"comments\": \"\"\r\n" +
		"        },\r\n" +
		"        \"nodePath\": [\r\n" +
		"          \"Top\",\r\n" +
		"          \"oneworldobservatory.com\"\r\n" +
		"        ]\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"        \\r\n" +
		"        \"tooltip\": \"Company node\",\r\n" +
		"        \"type\": \"company\",\r\n" +
		"        \"children\": [ ],\r\n" +
		"        \"details\": {\r\n" +
		"          \"name\": \"owo.dnsalias.com\",\r\n" +
		"          \"extra\": \"\",\r\n" +
		"          \"enabled\": true,\r\n" +
		"          \"match\": \"%owo.dnsalias.com\",\r\n" +
		"          \"type\": \"inject\",\r\n" +
		"          \"backendType\": \"https\",\r\n" +
		"          \"backendServer\": \"www.oneworldobservatory.com\",\r\n" +
		"          \"secureServer\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"updated\": false,\r\n" +
		"          \"comments\": \"\"\r\n" +
		"        },\r\n" +
		"        \"nodePath\": [\r\n" +
		"          \"Top\",\r\n" +
		"          \"owo.dnsalias.com\"\r\n" +
		"        ]\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"        \\r\n" +
		"        \"tooltip\": \"Company node\",\r\n" +
		"        \"type\": \"company\",\r\n" +
		"        \"children\": [ ],\r\n" +
		"        \"details\": {\r\n" +
		"          \"name\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"extra\": \"\",\r\n" +
		"          \"enabled\": true,\r\n" +
		"          \"match\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"type\": \"inject\",\r\n" +
		"          \"backendType\": \"https\",\r\n" +
		"          \"backendServer\": \"owo.secure-cdn.na.accessoticketing.com\",\r\n" +
		"          \"secureServer\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"updated\": false,\r\n" +
		"          \"comments\": \"\"\r\n" +
		"        },\r\n" +
		"        \"nodePath\": [\r\n" +
		"          \"Top\",\r\n" +
		"          \"owo.secure.dnsalias.com\"\r\n" +
		"        ]\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"        \\r\n" +
		"        \"tooltip\": \"Company node\",\r\n" +
		"        \"type\": \"company\",\r\n" +
		"        \"children\": [ ],\r\n" +
		"        \"details\": {\r\n" +
		"          \"name\": \"test.bskinz.com\",\r\n" +
		"          \"extra\": \"\",\r\n" +
		"          \"enabled\": true,\r\n" +
		"          \"type\": \"inject\",\r\n" +
		"          \"backendType\": \"https\",\r\n" +
		"          \"backendServer\": \"bskinz.com\",\r\n" +
		"          \"match\": \"test.bskinz.com\",\r\n" +
		"          \"secureServer\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"updated\": false,\r\n" +
		"          \"comments\": \"\"\r\n" +
		"        },\r\n" +
		"        \"nodePath\": [\r\n" +
		"          \"Top\",\r\n" +
		"          \"test.bskinz.com\"\r\n" +
		"        ]\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"        \\r\n" +
		"        \"tooltip\": \"Company node\",\r\n" +
		"        \"type\": \"company\",\r\n" +
		"        \"children\": [ ],\r\n" +
		"        \"details\": {\r\n" +
		"          \"name\": \"www.oneworldobservatory.com\",\r\n" +
		"          \"extra\": \"\",\r\n" +
		"          \"enabled\": true,\r\n" +
		"          \"match\": \"www.oneworldobservatory.com\",\r\n" +
		"          \"type\": \"inject\",\r\n" +
		"          \"backendType\": \"https\",\r\n" +
		"          \"backendServer\": \"www.oneworldobservatory.com\",\r\n" +
		"          \"secureServer\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"updated\": false,\r\n" +
		"          \"comments\": \"\"\r\n" +
		"        },\r\n" +
		"        \"nodePath\": [\r\n" +
		"          \"Top\",\r\n" +
		"          \"www.oneworldobservatory.com\"\r\n" +
		"        ]\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"        \\r\n" +
		"        \"tooltip\": \"Company node\",\r\n" +
		"        \"type\": \"company\",\r\n" +
		"        \"children\": [ ],\r\n" +
		"        \"details\": {\r\n" +
		"          \"name\": \"www.owo-web-uat.corebine.com\",\r\n" +
		"          \"extra\": \"\",\r\n" +
		"          \"enabled\": true,\r\n" +
		"          \"match\": \"www.owo-web-uat.corebine.com\",\r\n" +
		"          \"type\": \"inject\",\r\n" +
		"          \"backendType\": \"https\",\r\n" +
		"          \"backendServer\": \"www.oneworldobservatory.com\",\r\n" +
		"          \"secureServer\": \"owo.secure.dnsalias.com\",\r\n" +
		"          \"updated\": false,\r\n" +
		"          \"comments\": \"\"\r\n" +
		"        },\r\n" +
		"        \"nodePath\": [\r\n" +
		"          \"Top\",\r\n" +
		"          \"www.owo-web-uat.corebine.com\"\r\n" +
		"        ]\r\n" +
		"      }\r\n" +
		"    ],\r\n" +
		"    \"nodePath\": [\r\n" +
		"      \"Top\"\r\n" +
		"    ]\r\n" +
		"  }\r\n" +
		"]";
  /* This is the test data used to test (with Junit5) HDLmTree. This test data is
     fixed so that the tests yield reproducible results. The test data was obtained
     from a live system, so that it is quite complex and useful. */
	protected static final String  jsonGetProxyStrSaved3 =
		"{" +
		  "\"rows_returned\": 1," +
		  "\"comments\": \"io for test_2\"," +
		  "\"data\": [" +
		    "{" +
		      "\"company\": \"example.com\"," +
		      "\"content\": \"proxy_javac\"," +
		      "\"created\": 1559583166520," +
		      "\"division\": \"example.com\"," +
		      "\"enabled\": true," +
		      "\"id\": \"770058f59d1b4ae694bf2a50963ba5da\"," +
		      "\"meta_behavior\": \"047e74eac8714fe9b185ea8c2e8cc00c\"," +
		      "\"meta_classification\": \"\"," +
		      "\"meta_datatag\": \"[]\"," +
		      "\"meta_schema\": \"\"," +
		      "\"mods\": \"[{\\\"tooltip\\\":\\\"Top node of the node tree\\\",\\\"type\\\":\\\"top\\\",\\\"children\\\":[" +
		        "{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"legends-owo.dnsalias.com\\\",\\\"match\\\":\\\"legends-owo.dnsalias.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"https\\\",\\\"backendServer\\\":\\\"oneworldobservatory.com\\\",\\\"secureServer\\\":\\\"legends-owo-secure.dnsalias.com\\\",\\\"updated\\\":true},\\\"nodePath\\\":[\\\"Top\\\",\\\"legends-owo.dnsalias.com\\\"]}," +
		        "{\\\"tooltip\\\":\\\"Company node\\\",\\\"type\\\":\\\"company\\\",\\\"children\\\":[],\\\"details\\\":{\\\"name\\\":\\\"oneworldobservatory.com\\\",\\\"match\\\":\\\"oneworldobservatory.com\\\",\\\"extra\\\":\\\"\\\",\\\"enabled\\\":true,\\\"type\\\":\\\"inject\\\",\\\"backendType\\\":\\\"http\\\",\\\"backendServer\\\":\\\"xx.com\\\",\\\"secureServer\\\":\\\"xx.yy.com\\\",\\\"updated\\\":false},\\\"nodePath\\\":[\\\"Top\\\",\\\"oneworldobservatory.com\\\"]}],\\\"nodePath\\\":[\\\"Top\\\"]}]\"," +
		      "\"owner\": \"7fe50ec9e6a64544a90d34ef8e496842\"," +
		      "\"relationships\": \"\"," +
		      "\"site\": \"example.com\"," +
		      "\"updated\": 1563127342250" +
		    "}" +
		  "]," +
		  "\"retcode\": 1" +
		"}";
  /* This is the test data used to test (with Junit5) HDLmMod. This test data is
     fixed so that the tests yield reproducible results. The test data was obtained
     originally from a live system, so that it is quite complex and useful. */
  private static final String  magentoNeveJacketHtmlStrPart1 = "" +
  	"<!doctype html>\r\n<html lang=\"en\">\r\n   <head prefix=\"og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# product: http://ogp.me/ns/product#\">\r\n      <script>\r\n         var require = {\r\n             \"baseUrl\": \"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US\"\r\n         };\r\n      </script>\r\n      <meta charset=\"utf-8\"/>\r\n      <meta name=\"description\" content=\"&lt;p&gt;If you\'re constantly on the move, the Neve Studio Dance Jacket is for you. It\'s not just for dance, either, with a tight fit that works as a mid-layer. The reversible design makes it even more versatile.&lt;/p&gt;\r\n         &lt;p&gt;&amp;bull; Bright blue 1/4 zip pullover.&lt;br /\"/>\r\n      <meta name=\"keywords\" content=\"Neve Studio Dance Jacket\"/>\r\n      <meta name=\"robots\" content=\"INDEX,FOLLOW\"/>\r\n      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no\"/>\r\n      <meta name=\"format-detection\" content=\"telephone=no\"/>\r\n      <title>Neve Studio Dance Jacket - Jackets - Tops - Women</title>\r\n      <link  rel=\"stylesheet\" type=\"text/css\"  media=\"all\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/mage/calendar.css\" />\r\n      <link  rel=\"stylesheet\" type=\"text/css\"  media=\"all\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/css/styles-m.css\" />\r\n      <link  rel=\"stylesheet\" type=\"text/css\"  media=\"all\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/mage/gallery/gallery.css\" />\r\n      <link  rel=\"stylesheet\" type=\"text/css\"  media=\"all\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/Magento_Swatches/css/swatches.css\" />\r\n      <link  rel=\"stylesheet\" type=\"text/css\"  media=\"screen and (min-width: 768px)\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/css/styles-l.css\" />\r\n      <link  rel=\"stylesheet\" type=\"text/css\"  media=\"print\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/css/print.css\" />\r\n      <link  rel=\"icon\" type=\"image/x-icon\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/Magento_Theme/favicon.ico\" />\r\n      <link  rel=\"shortcut icon\" type=\"image/x-icon\" href=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/Magento_Theme/favicon.ico\" />\r\n      <script  type=\"text/javascript\"  src=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/requirejs/require.js\"></script>\r\n      <script  type=\"text/javascript\"  src=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/mage/requirejs/mixins.js\"></script>\r\n      <script  type=\"text/javascript\"  src=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/requirejs-config.js\"></script>\r\n      <link  rel=\"stylesheet\" type=\"text/css\"  media=\"all\" href=\"http://example.com/pub/media/styles.css\" />\r\n      <meta property=\"og:type\" content=\"og:product\" />\r\n      <meta property=\"og:title\" content=\"Neve&#x20;Studio&#x20;Dance&#x20;Jacket\" />\r\n      <meta property=\"og:image\" content=\"http://example.com/pub/media/catalog/product/cache/0f831c1845fc143d00d6d1ebc49f446a/w/j/wj11-blue_main.jpg\" />\r\n      <meta property=\"og:description\" content=\"\" />\r\n      <meta property=\"og:url\" content=\"http://example.com/women/tops-women/jackets-women/neve-studio-dance-jacket.html\" />\r\n      <meta property=\"product:price:amount\" content=\"69\"/>\r\n      <meta property=\"product:price:currency\" content=\"USD\"/>\r\n   </head>\r\n   <body data-container=\"body\" data-mage-init=\'{\"loaderAjax\": {}, \"loader\": { \"icon\": \"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/images/loader-2.gif\"}}\' itemtype=\"http://schema.org/Product\" itemscope=\"itemscope\" class=\"page-product-configurable catalog-product-view product-neve-studio-dance-jacket categorypath-women-tops-women-jackets-women category-jackets-women page-layout-1column\">\r\n      <script>\r\n         try {\r\n             if (!window.localStorage || !window.sessionStorage) {\r\n                 throw new Error();\r\n             }\r\n         \r\n             localStorage.setItem(\'storage_test\', 1);\r\n             localStorage.removeItem(\'storage_test\');\r\n         } catch(e) {\r\n             (function () {\r\n                 var Storage = function (type) {\r\n                     var data;\r\n         \r\n                     function createCookie(name, value, days) {\r\n                         var date, expires;\r\n         \r\n                         if (days) {\r\n                             date = new Date();\r\n                             date.setTime(date.getTime()+(days * 24 * 60 * 60 * 1000));\r\n                             expires = \'; expires=\' + date.toGMTString();\r\n                         } else {\r\n                             expires = \'\';\r\n                         }\r\n                         document.cookie = name + \'=\' + value+expires+\'; path=/\';\r\n                     }\r\n         \r\n                     function readCookie(name) {\r\n                         var nameEQ = name + \'=\',\r\n                             ca = document.cookie.split(\';\'),\r\n                             i = 0,\r\n                             c;\r\n         \r\n                         for (i=0; i < ca.length; i++) {\r\n                             c = ca[i];\r\n         \r\n                             while (c.charAt(0) === \' \') {\r\n                                 c = c.substring(1,c.length);\r\n                             }\r\n         \r\n                             if (c.indexOf(nameEQ) === 0) {\r\n                                 return c.substring(nameEQ.length, c.length);\r\n                             }\r\n                         }\r\n         \r\n                         return null;\r\n                     }\r\n         \r\n                     function setData(data) {\r\n                         data = encodepathComponent(JSON.stringify(data));\r\n                         createCookie(type === \'session\' ? getSessionName() : \'localStorage\', data, 365);\r\n                     }\r\n         \r\n                     function clearData() {\r\n                         createCookie(type === \'session\' ? getSessionName() : \'localStorage\', \'\', 365);\r\n                     }\r\n         \r\n                     function getData() {\r\n                         var data = type === \'session\' ? readCookie(getSessionName()) : readCookie(\'localStorage\');\r\n         \r\n                         return data ? JSON.parse(decodepathComponent(data)) : {};\r\n                     }\r\n         \r\n                     function getSessionName() {\r\n                         if (!window.name) {\r\n                             window.name = new Date().getTime();\r\n                         }\r\n         \r\n                         return \'sessionStorage\' + window.name;\r\n                     }\r\n         \r\n                     data = getData();\r\n         \r\n                     return {\r\n                         length: 0,\r\n                         clear: function () {\r\n                             data = {};\r\n                             this.length = 0;\r\n                             clearData();\r\n                         },\r\n         \r\n                         getItem: function (key) {\r\n                             return data[key] === undefined ? null : data[key];\r\n                         },\r\n         \r\n                         key: function (i) {\r\n                             var ctr = 0,\r\n                                 k;\r\n         \r\n                             for (k in data) {\r\n                                 if (ctr.toString() === i.toString()) {\r\n                                     return k;\r\n                                 } else {\r\n                                     ctr++\r\n                                 }\r\n                             }\r\n         \r\n                             return null;\r\n                         },\r\n         \r\n                         removeItem: function (key) {\r\n                             delete data[key];\r\n                             this.length--;\r\n                             setData(data);\r\n                         },\r\n         \r\n                         setItem: function (key, value) {\r\n                             data[key] = value.toString();\r\n                             this.length++;\r\n                             setData(data);\r\n                         }\r\n                     };\r\n                 };\r\n         \r\n                 window.localStorage.__proto__ = window.localStorage = new Storage(\'local\');\r\n                 window.sessionStorage.__proto__ = window.sessionStorage = new Storage(\'session\');\r\n             })();\r\n         }\r\n      </script>\r\n      <script>\r\n         require.config({\r\n             deps: [\r\n                 \'jquery\',\r\n                 \'mage/translate\',\r\n                 \'jquery/jquery-storageapi\'\r\n             ],\r\n             callback: function ($) {\r\n                 \'use strict\';\r\n         \r\n                 var dependencies = [],\r\n                     versionObj;\r\n         \r\n                 $.initNamespaceStorage(\'mage-translation-storage\');\r\n                 $.initNamespaceStorage(\'mage-translation-file-version\');\r\n                 versionObj = $.localStorage.get(\'mage-translation-file-version\');\r\n         \r\n                 \r\n                 if (versionObj.version !== \'657ec88bc2387bdcb009970987788e5abea2d8ec\') {\r\n                     dependencies.push(\r\n                         \'text!js-translation.json\'\r\n          ";
  private static final String  magentoNeveJacketHtmlStrPart2 = "" +
  	"           );\r\n         \r\n                 }\r\n         \r\n                 require.config({\r\n                     deps: dependencies,\r\n                     callback: function (string) {\r\n                         if (typeof string === \'string\') {\r\n                             $.mage.translate.add(JSON.parse(string));\r\n                             $.localStorage.set(\'mage-translation-storage\', string);\r\n                             $.localStorage.set(\r\n                                 \'mage-translation-file-version\',\r\n                                 {\r\n                                     version: \'657ec88bc2387bdcb009970987788e5abea2d8ec\'\r\n                                 }\r\n                             );\r\n                         } else {\r\n                             $.mage.translate.add($.localStorage.get(\'mage-translation-storage\'));\r\n                         }\r\n                     }\r\n                 });\r\n             }\r\n         });\r\n      </script>\r\n      <script type=\"text/x-magento-init\">\r\n         {\r\n             \"*\": {\r\n                 \"mage/cookies\": {\r\n                     \"expires\": null,\r\n                     \"path\": \"/\",\r\n                     \"domain\": \".example.com\",\r\n                     \"secure\": false,\r\n                     \"lifetime\": \"3600\"\r\n                 }\r\n             }\r\n         }\r\n      </script>\r\n      <noscript>\r\n         <div class=\"message global noscript\">\r\n            <div class=\"content\">\r\n               <p>\r\n                  <strong>JavaScript seems to be disabled in your browser.</strong>\r\n                  <span>For the best experience on our site, be sure to turn on Javascript in your browser.</span>\r\n               </p>\r\n            </div>\r\n         </div>\r\n      </noscript>\r\n      <script type=\"text/x-magento-init\">\r\n         {\r\n             \"*\": {\r\n                 \"trackingCode\": {\r\n                     \"isEnabled\": \"\"\r\n                 }\r\n             }\r\n         }\r\n      </script>\r\n      <div class=\"page-wrapper\">\r\n         <header class=\"page-header\">\r\n            <div class=\"panel wrapper\">\r\n               <div class=\"panel header\">\r\n                  <a class=\"action skip contentarea\" href=\"#contentarea\"><span>Skip to Content</span></a>\r\n                  <ul class=\"header links\">\r\n                     <li class=\"greet welcome\" data-bind=\"scope: \'customer\'\">\r\n                        <!-- ko if: customer().fullname  -->\r\n                        <span data-bind=\"text: new String(\'Welcome, %1!\').replace(\'%1\', customer().firstname)\">\r\n                        </span>\r\n                        <!-- /ko -->\r\n                        <!-- ko ifnot: customer().fullname  -->\r\n                        <span data-bind=\'html:\"Default&#x20;welcome&#x20;msg&#x21;\"\'></span>\r\n                        <!-- /ko -->\r\n                     </li>\r\n                     <script type=\"text/x-magento-init\">\r\n                        {\r\n                            \"*\": {\r\n                                \"Magento_Ui/js/core/app\": {\r\n                                    \"components\": {\r\n                                        \"customer\": {\r\n                                            \"component\": \"Magento_Customer/js/view/customer\"\r\n                                        }\r\n                                    }\r\n                                }\r\n                            }\r\n                        }\r\n                     </script>\r\n                     <li class=\"authorization-link\" data-label=\"or\">\r\n                        <a href=\"http://example.com/customer/account/login/referer/aHR0cDovL2V4YW1wbGUuY29tL25ldmUtc3R1ZGlvLWRhbmNlLWphY2tldC5odG1s/\">\r\n                        Sign In    </a>\r\n                     </li>\r\n                     <li><a href=\"http://example.com/customer/account/create/\" >Create an Account</a></li>\r\n                  </ul>\r\n               </div>\r\n            </div>\r\n            <div class=\"header content\">\r\n               <span data-action=\"toggle-nav\" class=\"action nav-toggle\"><span>Toggle Nav</span></span>\r\n               <a class=\"logo\" href=\"http://example.com/\" title=\"\">\r\n               <img src=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/images/logo.svg\"\r\n                  alt=\"\"\r\n                  width=\"148\"             height=\"43\"        />\r\n               </a>\r\n               <div data-block=\"minicart\" class=\"minicart-wrapper\">\r\n                  <a class=\"action showcart\" href=\"http://example.com/checkout/cart/\"\r\n                     data-bind=\"scope: \'minicart_content\'\">\r\n                     <span class=\"text\">My Cart</span>\r\n                     <span class=\"counter qty empty\"\r\n                        data-bind=\"css: { empty: !!getCartParam(\'summary_count\') == false }, blockLoader: isLoading\">\r\n                        <span class=\"counter-number\">\r\n                           <!-- ko text: getCartParam(\'summary_count\') --><!-- /ko -->\r\n                        </span>\r\n                        <span class=\"counter-label\">\r\n                           <!-- ko if: getCartParam(\'summary_count\') -->\r\n                           <!-- ko text: getCartParam(\'summary_count\') --><!-- /ko -->\r\n                           <!-- ko i18n: \'items\' --><!-- /ko -->\r\n                           <!-- /ko -->\r\n                        </span>\r\n                     </span>\r\n                  </a>\r\n                  <div class=\"block block-minicart empty\"\r\n                     data-role=\"dropdownDialog\"\r\n                     data-mage-init=\'{\"dropdownDialog\":{\r\n                     \"appendTo\":\"[data-block=minicart]\",\r\n                     \"triggerTarget\":\".showcart\",\r\n                     \"timeout\": \"2000\",\r\n                     \"closeOnMouseLeave\": false,\r\n                     \"closeOnEscape\": true,\r\n                     \"triggerClass\":\"active\",\r\n                     \"parentClass\":\"active\",\r\n                     \"buttons\":[]}}\'>\r\n                     <div id=\"minicart-content-wrapper\" data-bind=\"scope: \'minicart_content\'\">\r\n                        <!-- ko template: getTemplate() --><!-- /ko -->\r\n                     </div>\r\n                  </div>\r\n                  <script>\r\n                     window.checkout = {\"shoppingCartUrl\":\"http:\\/\\/example.com\\/checkout\\/cart\\/\",\"checkoutUrl\":\"http:\\/\\/example.com\\/checkout\\/\",\"updateItemQtyUrl\":\"http:\\/\\/example.com\\/checkout\\/sidebar\\/updateItemQty\\/\",\"removeItemUrl\":\"http:\\/\\/example.com\\/checkout\\/sidebar\\/removeItem\\/\",\"imageTemplate\":\"Magento_Catalog\\/product\\/image_with_borders\",\"baseUrl\":\"http:\\/\\/example.com\\/\",\"minicartMaxItemsVisible\":5,\"websiteId\":\"1\",\"maxItemsToDisplay\":10,\"customerLoginUrl\":\"http:\\/\\/example.com\\/customer\\/account\\/login\\/\",\"isRedirectRequired\":false,\"autocomplete\":\"off\",\"captcha\":{\"user_login\":{\"isCaseSensitive\":false,\"imageHeight\":50,\"imageSrc\":\"\",\"refreshUrl\":\"http:\\/\\/example.com\\/captcha\\/refresh\\/\",\"isRequired\":false},\"guest_checkout\":{\"isCaseSensitive\":false,\"imageHeight\":50,\"imageSrc\":\"\",\"refreshUrl\":\"http:\\/\\/example.com\\/captcha\\/refresh\\/\",\"isRequired\":false}}};\r\n                  </script>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\r\n                         \"[data-block=\'minicart\']\": {\r\n                             \"Magento_Ui/js/core/app\": {\"components\":{\"minicart_content\":{\"children\":{\"subtotal.container\":{\"children\":{\"subtotal\":{\"children\":{\"subtotal.totals\":{\"config\":{\"display_cart_subtotal_incl_tax\":0,\"display_cart_subtotal_excl_tax\":1,\"template\":\"Magento_Tax\\/checkout\\/minicart\\/subtotal\\/totals\"},\"children\":{\"subtotal.totals.msrp\":{\"component\":\"Magento_Msrp\\/js\\/view\\/checkout\\/minicart\\/subtotal\\/totals\",\"config\":{\"displayArea\":\"minicart-subtotal-hidden\",\"template\":\"Magento_Msrp\\/checkout\\/minicart\\/subtotal\\/totals\"}}},\"component\":\"Magento_Tax\\/js\\/view\\/checkout\\/minicart\\/subtotal\\/totals\"}},\"component\":\"uiComponent\",\"config\":{\"template\":\"Magento_Checkout\\/minicart\\/subtotal\"}}},\"component\":\"uiComponent\",\"config\":{\"displayArea\":\"subtotalContainer\"}},\"item.renderer\":{\"component\":\"uiComponent\",\"config\":{\"displayArea\":\"defaultRenderer\",\"template\":\"Magento_Checkout\\/minicart\\/item\\/default\"},\"children\":{\"item.image\":{\"component\":\"Magento_Catalog\\/js\\/view\\/image\",\"config\":{\"template\":\"Magento_Catalog\\/product\\/image\",\"displayArea\":\"itemImage\"}},\"checkout.cart.item.price.sidebar\":{\"component\":\"uiComponent\",\"config\":{\"template\":\"Magento_Checkout\\/minicart\\/item\\/price\",\"displayArea\":\"priceSidebar\"}}}},\"extra_info\":{\"component\":\"uiComponent\",\"config\":{\"displayArea\":\"extraInfo\"}},\"promotion\":{\"component\":\"uiComponent\",\"config\":{\"displayArea\":\"promotion\"}}},\"config\":{\"itemRenderer\":{\"default\":\"defaultRenderer\",\"simple\":\"defaultRenderer\",\"virtual\":\"defaultRenderer\"},\"template\":\"Magento_Checkout\\/minicart\\/content\"},\"component\":\"Magento_Checkout\\/js\\/view\\/minicart\"}},\"types\":[]}        },\r\n                         \"*\": {\r\n                             \"Magento_Ui/js/block-loader\": \"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/images/loader-1.gif\"\r\n                         }\r\n                     }\r\n                  </script>\r\n               </div>\r\n               <div class=\"block block-search\">\r\n                  <div class=\"block block-title\"><strong>Search</strong></div>\r\n        ";
  private static final String  magentoNeveJacketHtmlStrPart3 = "" +
  	"          <div class=\"block block-content\">\r\n                     <form class=\"form minisearch\" id=\"search_mini_form\" action=\"http://example.com/catalogsearch/result/\" method=\"get\">\r\n                        <div class=\"field search\">\r\n                           <label class=\"label\" for=\"search\" data-role=\"minisearch-label\">\r\n                           <span>Search</span>\r\n                           </label>\r\n                           <div class=\"control\">\r\n                              <input id=\"search\"\r\n                                 data-mage-init=\'{\"quickSearch\":{\r\n                                 \"formSelector\":\"#search_mini_form\",\r\n                                 \"url\":\"http://example.com/search/ajax/suggest/\",\r\n                                 \"destinationSelector\":\"#search_autocomplete\"}\r\n                                 }\'\r\n                                 type=\"text\"\r\n                                 name=\"q\"\r\n                                 value=\"\"\r\n                                 placeholder=\"Search entire store here...\"\r\n                                 class=\"input-text\"\r\n                                 maxlength=\"128\"\r\n                                 role=\"combobox\"\r\n                                 aria-haspopup=\"false\"\r\n                                 aria-autocomplete=\"both\"\r\n                                 autocomplete=\"off\"/>\r\n                              <div id=\"search_autocomplete\" class=\"search-autocomplete\"></div>\r\n                              <div class=\"nested\">\r\n                                 <a class=\"action advanced\" href=\"http://example.com/catalogsearch/advanced/\" data-action=\"advanced-search\">\r\n                                 Advanced Search    </a>\r\n                              </div>\r\n                           </div>\r\n                        </div>\r\n                        <div class=\"actions\">\r\n                           <button type=\"submit\"\r\n                              title=\"Search\"\r\n                              class=\"action search\">\r\n                           <span>Search</span>\r\n                           </button>\r\n                        </div>\r\n                     </form>\r\n                  </div>\r\n               </div>\r\n               <ul class=\"compare wrapper\">\r\n                  <li class=\"item link compare\" data-bind=\"scope: \'compareProducts\'\" data-role=\"compare-products-link\">\r\n                     <a class=\"action compare no-display\" title=\"Compare Products\"\r\n                        data-bind=\"attr: {\'href\': compareProducts().listUrl}, css: {\'no-display\': !compareProducts().count}\"\r\n                        >\r\n                     Compare Products        <span class=\"counter qty\" data-bind=\"text: compareProducts().countCaption\"></span>\r\n                     </a>\r\n                  </li>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\"[data-role=compare-products-link]\": {\"Magento_Ui/js/core/app\": {\"components\":{\"compareProducts\":{\"component\":\"Magento_Catalog\\/js\\/view\\/compare-products\"}}}}}\r\n                  </script>\r\n               </ul>\r\n            </div>\r\n         </header>\r\n         <div class=\"sections nav-sections\">\r\n            <div class=\"section-items nav-sections-items\" data-mage-init=\'{\"tabs\":{\"openedState\":\"active\"}}\'>\r\n               <div class=\"section-item-title nav-sections-item-title\" data-role=\"collapsible\">\r\n                  <a class=\"nav-sections-item-switch\" data-toggle=\"switch\" href=\"#store.menu\">Menu</a>\r\n               </div>\r\n               <div class=\"section-item-content nav-sections-item-content\" id=\"store.menu\" data-role=\"content\">\r\n                  <nav class=\"navigation\" data-action=\"navigation\">\r\n                     <ul data-mage-init=\'{\"menu\":{\"responsive\":true, \"expanded\":true, \"position\":{\"my\":\"left top\",\"at\":\"left bottom\"}}}\'>\r\n                        <li  class=\"level0 nav-1 first level-top\"><a href=\"http://example.com/what-is-new.html\"  class=\"level-top\" ><span>What&#039;s New</span></a></li>\r\n                        <li  class=\"level0 nav-2 has-active level-top parent\">\r\n                           <a href=\"http://example.com/women.html\"  class=\"level-top\" ><span>Women</span></a>\r\n                           <ul class=\"level0 submenu\">\r\n                              <li  class=\"level1 nav-2-1 first has-active parent\">\r\n                                 <a href=\"http://example.com/women/tops-women.html\" ><span>Tops</span></a>\r\n                                 <ul class=\"level1 submenu\">\r\n                                    <li  class=\"level2 nav-2-1-1 first active\"><a href=\"http://example.com/women/tops-women/jackets-women.html\" ><span>Jackets</span></a></li>\r\n                                    <li  class=\"level2 nav-2-1-2\"><a href=\"http://example.com/women/tops-women/hoodies-and-sweatshirts-women.html\" ><span>Hoodies &amp; Sweatshirts</span></a></li>\r\n                                    <li  class=\"level2 nav-2-1-3\"><a href=\"http://example.com/women/tops-women/tees-women.html\" ><span>Tees</span></a></li>\r\n                                    <li  class=\"level2 nav-2-1-4 last\"><a href=\"http://example.com/women/tops-women/tanks-women.html\" ><span>Bras &amp; Tanks</span></a></li>\r\n                                 </ul>\r\n                              </li>\r\n                              <li  class=\"level1 nav-2-2 last parent\">\r\n                                 <a href=\"http://example.com/women/bottoms-women.html\" ><span>Bottoms</span></a>\r\n                                 <ul class=\"level1 submenu\">\r\n                                    <li  class=\"level2 nav-2-2-1 first\"><a href=\"http://example.com/women/bottoms-women/pants-women.html\" ><span>Pants</span></a></li>\r\n                                    <li  class=\"level2 nav-2-2-2 last\"><a href=\"http://example.com/women/bottoms-women/shorts-women.html\" ><span>Shorts</span></a></li>\r\n                                 </ul>\r\n                              </li>\r\n                           </ul>\r\n                        </li>\r\n                        <li  class=\"level0 nav-3 level-top parent\">\r\n                           <a href=\"http://example.com/men.html\"  class=\"level-top\" ><span>Men</span></a>\r\n                           <ul class=\"level0 submenu\">\r\n                              <li  class=\"level1 nav-3-1 first parent\">\r\n                                 <a href=\"http://example.com/men/tops-men.html\" ><span>Tops</span></a>\r\n                                 <ul class=\"level1 submenu\">\r\n                                    <li  class=\"level2 nav-3-1-1 first\"><a href=\"http://example.com/men/tops-men/jackets-men.html\" ><span>Jackets</span></a></li>\r\n                                    <li  class=\"level2 nav-3-1-2\"><a href=\"http://example.com/men/tops-men/hoodies-and-sweatshirts-men.html\" ><span>Hoodies &amp; Sweatshirts</span></a></li>\r\n                                    <li  class=\"level2 nav-3-1-3\"><a href=\"http://example.com/men/tops-men/tees-men.html\" ><span>Tees</span></a></li>\r\n                                    <li  class=\"level2 nav-3-1-4 last\"><a href=\"http://example.com/men/tops-men/tanks-men.html\" ><span>Tanks</span></a></li>\r\n                                 </ul>\r\n                              </li>\r\n                              <li  class=\"level1 nav-3-2 last parent\">\r\n                                 <a href=\"http://example.com/men/bottoms-men.html\" ><span>Bottoms</span></a>\r\n                                 <ul class=\"level1 submenu\">\r\n                                    <li  class=\"level2 nav-3-2-1 first\"><a href=\"http://example.com/men/bottoms-men/pants-men.html\" ><span>Pants</span></a></li>\r\n                                    <li  class=\"level2 nav-3-2-2 last\"><a href=\"http://example.com/men/bottoms-men/shorts-men.html\" ><span>Shorts</span></a></li>\r\n                                 </ul>\r\n                              </li>\r\n                           </ul>\r\n                        </li>\r\n                        <li  class=\"level0 nav-4 level-top parent\">\r\n                           <a href=\"http://example.com/gear.html\"  class=\"level-top\" ><span>Gear</span></a>\r\n                           <ul class=\"level0 submenu\">\r\n                              <li  class=\"level1 nav-4-1 first\"><a href=\"http://example.com/gear/bags.html\" ><span>Bags</span></a></li>\r\n                              <li  class=\"level1 nav-4-2\"><a href=\"http://example.com/gear/fitness-equipment.html\" ><span>Fitness Equipment</span></a></li>\r\n                              <li  class=\"level1 nav-4-3 last\"><a href=\"http://example.com/gear/watches.html\" ><span>Watches</span></a></li>\r\n                           </ul>\r\n                        </li>\r\n                        <li  class=\"level0 nav-5 level-top parent\">\r\n                           <a href=\"http://example.com/training.html\"  class=\"level-top\" ><span>Training</span></a>\r\n                           <ul class=\"level0 submenu\">\r\n                              <li  class=\"level1 nav-5-1 first last\"><a href=\"http://example.com/training/training-video.html\" ><span>Video Download</span></a></li>\r\n                           </ul>\r\n                        </li>\r\n                        <li  class=\"level0 nav-6 last level-top\"><a href=\"http://example.com/sale.html\"  class=\"level-top\" ><span>Sale</span></a></li>\r\n                     </ul>\r\n                  </nav>\r\n               </div>\r\n               <div class=\"section-item-title nav-sections-item-title\" data-role=\"collapsible\">\r\n                  <a class=\"nav-sections-item-switch\" data-toggle=\"switch\" href=\"#store.links\">Account</a>\r\n      ";
  private static final String  magentoNeveJacketHtmlStrPart4 = "" +
  	"         </div>\r\n               <div class=\"section-item-content nav-sections-item-content\" id=\"store.links\" data-role=\"content\">\r\n                  <!-- Account links -->\r\n               </div>\r\n            </div>\r\n         </div>\r\n         <div class=\"breadcrumbs\">\r\n            <ul class=\"items\">\r\n               <li class=\"item home\">\r\n                  <a href=\"http://example.com/\" title=\"Go to Home Page\">Home</a>\r\n               </li>\r\n               <li class=\"item category20\">\r\n                  <a href=\"http://example.com/women.html\" title=\"\">Women</a>\r\n               </li>\r\n               <li class=\"item category21\">\r\n                  <a href=\"http://example.com/women/tops-women.html\" title=\"\">Tops</a>\r\n               </li>\r\n               <li class=\"item category23\">\r\n                  <a href=\"http://example.com/women/tops-women/jackets-women.html\" title=\"\">Jackets</a>\r\n               </li>\r\n               <li class=\"item product\">\r\n                  <strong>Neve Studio Dance Jacket</strong>\r\n               </li>\r\n            </ul>\r\n         </div>\r\n         <main id=\"maincontent\" class=\"page-main\">\r\n            <a id=\"contentarea\" tabindex=\"-1\"></a>\r\n            <div class=\"page messages\">\r\n               <div data-placeholder=\"messages\"></div>\r\n               <div data-bind=\"scope: \'messages\'\">\r\n                  <!-- ko if: cookieMessages && cookieMessages.length > 0 -->\r\n                  <div role=\"alert\" data-bind=\"foreach: { data: cookieMessages, as: \'message\' }\" class=\"messages\">\r\n                     <div data-bind=\"attr: {\r\n                        class: \'message-\' + message.type + \' \' + message.type + \' message\',\r\n                        \'data-ui-id\': \'message-\' + message.type\r\n                        }\">\r\n                        <div data-bind=\"html: message.text\"></div>\r\n                     </div>\r\n                  </div>\r\n                  <!-- /ko -->\r\n                  <!-- ko if: messages().messages && messages().messages.length > 0 -->\r\n                  <div role=\"alert\" data-bind=\"foreach: { data: messages().messages, as: \'message\' }\" class=\"messages\">\r\n                     <div data-bind=\"attr: {\r\n                        class: \'message-\' + message.type + \' \' + message.type + \' message\',\r\n                        \'data-ui-id\': \'message-\' + message.type\r\n                        }\">\r\n                        <div data-bind=\"html: message.text\"></div>\r\n                     </div>\r\n                  </div>\r\n                  <!-- /ko -->\r\n               </div>\r\n               <script type=\"text/x-magento-init\">\r\n                  {\r\n                      \"*\": {\r\n                          \"Magento_Ui/js/core/app\": {\r\n                              \"components\": {\r\n                                      \"messages\": {\r\n                                          \"component\": \"Magento_Theme/js/view/messages\"\r\n                                      }\r\n                                  }\r\n                              }\r\n                          }\r\n                  }\r\n               </script>\r\n            </div>\r\n            <div class=\"columns\">\r\n               <div class=\"column main\">\r\n                  <div class=\"product-info-main\">\r\n                     <div class=\"page-title-wrapper product\">\r\n                        <h1 class=\"page-title\"\r\n                           >\r\n                           <span class=\"base\" data-ui-id=\"page-title-wrapper\" itemprop=\"name\">Neve Studio Dance Jacket</span>    \r\n                        </h1>\r\n                     </div>\r\n                     <div class=\"product-reviews-summary\" itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">\r\n                        <div class=\"rating-summary\">\r\n                           <span class=\"label\"><span>Rating:</span></span>\r\n                           <div class=\"rating-result\" title=\"87%\">\r\n                              <span style=\"width:87%\">\r\n                              <span>\r\n                              <span itemprop=\"ratingValue\">87</span>% of <span itemprop=\"bestRating\">100</span>\r\n                              </span>\r\n                              </span>\r\n                           </div>\r\n                        </div>\r\n                        <div class=\"reviews-actions\">\r\n                           <a class=\"action view\"\r\n                              href=\"http://example.com/women/tops-women/jackets-women/neve-studio-dance-jacket.html#reviews\">\r\n                           <span itemprop=\"reviewCount\">3</span>&nbsp;\r\n                           <span>Reviews</span>\r\n                           </a>\r\n                           <a class=\"action add\" href=\"http://example.com/women/tops-women/jackets-women/neve-studio-dance-jacket.html#review-form\">Add Your Review</a>\r\n                        </div>\r\n                     </div>\r\n                     <div class=\"product-info-price\">\r\n                        <div class=\"price-box price-final_price\" data-role=\"priceBox\" data-product-id=\"1369\">\r\n                           <span class=\"price-container price-final_price tax weee\"\r\n                              itemprop=\"offers\" itemscope itemtype=\"http://schema.org/Offer\">\r\n                              <span  id=\"product-price-1369\"                data-price-amount=\"69\"\r\n                                 data-price-type=\"finalPrice\"\r\n                                 class=\"price-wrapper \">\r\n                              <span class=\"price\">$69.00</span>    </span>\r\n                              <meta itemprop=\"price\" content=\"69\" />\r\n                              <meta itemprop=\"priceCurrency\" content=\"USD\" />\r\n                           </span>\r\n                        </div>\r\n                        <div class=\"product-info-stock-sku\">\r\n                           <div class=\"stock available\" title=\"Availability\">\r\n                              <span>In stock</span>\r\n                           </div>\r\n                           <div class=\"product attribute sku\">\r\n                              <strong class=\"type\">SKU</strong>    \r\n                              <div class=\"value\" itemprop=\"sku\">WJ11</div>\r\n                           </div>\r\n                        </div>\r\n                     </div>\r\n                     <div class=\"price-box price-tier_price\" data-role=\"priceBox\" data-product-id=\"1369\">\r\n                        <script type=\"text/x-magento-template\" id=\"tier-prices-template\">\r\n                           <ul class=\"prices-tier items\">\r\n                               <% _.each(tierPrices, function(item, key) { %>\r\n                               <%  var priceStr = \'<span class=\"price-container price-tier_price\">\'\r\n                              + \'<span data-price-amount=\"\' + priceUtils.formatPrice(item.price, currencyFormat) + \'\"\'\r\n                              + \' data-price-type=\"\"\' + \' class=\"price-wrapper \">\'\r\n                              + \'<span class=\"price\">\' + priceUtils.formatPrice(item.price, currencyFormat) + \'</span>\'\r\n                              + \'</span>\'\r\n                              + \'</span>\'; %>\r\n                               <li class=\"item\">\r\n                                   <%= $t(\'Buy %1 for %2 each and\').replace(\'%1\', item.qty).replace(\'%2\', priceStr) %>\r\n                                       <strong class=\"benefit\">\r\n                                               <%= $t(\'save\') %><span class=\"percent tier-<%= key %>\">&nbsp;<%= item.percentage %></span>%\r\n                                       </strong>\r\n                               </li>\r\n                               <% }); %>\r\n                           </ul>\r\n                        </script>\r\n                        <div data-role=\"tier-price-block\"></div>\r\n                     </div>\r\n                     <div class=\"product-add-form\">\r\n                        <form data-product-sku=\"WJ11\"\r\n                           action=\"http://example.com/checkout/cart/add/uenc/aHR0cDovL2V4YW1wbGUuY29tL25ldmUtc3R1ZGlvLWRhbmNlLWphY2tldC5odG1s/product/1369/\" method=\"post\"\r\n                           id=\"product_addtocart_form\">\r\n                           <input type=\"hidden\" name=\"product\" value=\"1369\" />\r\n                           <input type=\"hidden\" name=\"selected_configurable_option\" value=\"\" />\r\n                           <input type=\"hidden\" name=\"related_product\" id=\"related-products-field\" value=\"\" />\r\n                           <input name=\"form_key\" type=\"hidden\" value=\"YxQj7rnvUlzGxMwS\" />                                    \r\n                           <div class=\"product-options-wrapper\" id=\"product-options-wrapper\">\r\n                              <div class=\"fieldset\" tabindex=\"0\">\r\n                                 <div class=\"swatch-opt\" data-role=\"swatch-options\"></div>\r\n                                 <script type=\"text/x-magento-init\">\r\n                                    {\r\n                                        \"[data-role=swatch-options]\": {\r\n                                            \"Magento_Swatches/js/swatch-renderer\": {\r\n                                                \"jsonConfig\": {\"attributes\":{\"141\":{\"id\":\"141\",\"code\":\"size\",\"label\":\"Size\",\"options\":[{\"id\":\"167\",\"label\":\"XS\",\"products\":[\"1354\",\"1355\",\"1356\"]},{\"id\":\"168\",\"label\":\"S\",\"products\":[\"1357\",\"1358\",\"1359\"]},{\"id\":\"169\",\"label\":\"M\",\"products\":[\"1360\",\"1361\",\"1362\"]},{\"id\":\"170\",\"label\":\"L\",\"products\":[\"1363\",\"1364\",\"1365\"]},{\"id\":\"171\",\"label\":";
  private static final String  magentoNeveJacketHtmlStrPart5 = "" +
  	"\"XL\",\"products\":[\"1366\",\"1367\",\"1368\"]}],\"position\":\"0\"},\"93\":{\"id\":\"93\",\"code\":\"color\",\"label\":\"Color\",\"options\":[{\"id\":\"49\",\"label\":\"Black\",\"products\":[\"1354\",\"1357\",\"1360\",\"1363\",\"1366\"]},{\"id\":\"50\",\"label\":\"Blue\",\"products\":[\"1355\",\"1358\",\"1361\",\"1364\",\"1367\"]},{\"id\":\"56\",\"label\":\"Orange\",\"products\":[\"1356\",\"1359\",\"1362\",\"1365\",\"1368\"]}],\"position\":\"1\"}},\"template\":\"$<%- data.price %>\",\"currencyFormat\":\"$%s\",\"optionPrices\":{\"1354\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1355\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1356\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1357\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1358\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1359\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1360\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1361\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1362\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1363\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1364\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1365\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1366\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1367\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1368\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]}},\"priceFormat\":{\"pattern\":\"$%s\",\"precision\":2,\"requiredPrecision\":2,\"decimalSymbol\":\".\",\"groupSymbol\":\",\",\"groupLength\":3,\"integerRequired\":1},\"prices\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69}},\"productId\":\"1369\",\"chooseText\":\"Choose an Option...\",\"images\":{\"1354\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1355\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1356\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1357\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1358\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1359\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1360\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1361\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1362\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/";
  private static final String  magentoNeveJacketHtmlStrPart6 = "" +
  	"wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1363\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1364\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1365\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1366\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1367\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1368\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}]},\"index\":{\"1354\":{\"141\":\"167\",\"93\":\"49\"},\"1355\":{\"141\":\"167\",\"93\":\"50\"},\"1356\":{\"141\":\"167\",\"93\":\"56\"},\"1357\":{\"141\":\"168\",\"93\":\"49\"},\"1358\":{\"141\":\"168\",\"93\":\"50\"},\"1359\":{\"141\":\"168\",\"93\":\"56\"},\"1360\":{\"141\":\"169\",\"93\":\"49\"},\"1361\":{\"141\":\"169\",\"93\":\"50\"},\"1362\":{\"141\":\"169\",\"93\":\"56\"},\"1363\":{\"141\":\"170\",\"93\":\"49\"},\"1364\":{\"141\":\"170\",\"93\":\"50\"},\"1365\":{\"141\":\"170\",\"93\":\"56\"},\"1366\":{\"141\":\"171\",\"93\":\"49\"},\"1367\":{\"141\":\"171\",\"93\":\"50\"},\"1368\":{\"141\":\"171\",\"93\":\"56\"}}},\r\n                                                \"jsonSwatchConfig\": {\"141\":{\"167\":{\"type\":\"0\",\"value\":\"XS\",\"label\":\"XS\"},\"168\":{\"type\":\"0\",\"value\":\"S\",\"label\":\"S\"},\"169\":{\"type\":\"0\",\"value\":\"M\",\"label\":\"M\"},\"170\":{\"type\":\"0\",\"value\":\"L\",\"label\":\"L\"},\"171\":{\"type\":\"0\",\"value\":\"XL\",\"label\":\"XL\"}},\"93\":{\"49\":{\"type\":\"1\",\"value\":\"#000000\",\"label\":\"Black\"},\"50\":{\"type\":\"1\",\"value\":\"#1857f7\",\"label\":\"Blue\"},\"56\":{\"type\":\"1\",\"value\":\"#eb6703\",\"label\":\"Orange\"}}},\r\n                                                \"mediaCallback\": \"http://example.com/swatches/ajax/media/\",\r\n                                                \"gallerySwitchStrategy\": \"prepend\"\r\n                                            },\r\n                                            \"Magento_Swatches/js/configurable-customer-data\": {\r\n                                                    \"swatchOptions\": {\"attributes\":{\"141\":{\"id\":\"141\",\"code\":\"size\",\"label\":\"Size\",\"options\":[{\"id\":\"167\",\"label\":\"XS\",\"products\":[\"1354\",\"1355\",\"1356\"]},{\"id\":\"168\",\"label\":\"S\",\"products\":[\"1357\",\"1358\",\"1359\"]},{\"id\":\"169\",\"label\":\"M\",\"products\":[\"1360\",\"1361\",\"1362\"]},{\"id\":\"170\",\"label\":\"L\",\"products\":[\"1363\",\"1364\",\"1365\"]},{\"id\":\"171\",\"label\":\"XL\",\"products\":[\"1366\",\"1367\",\"1368\"]}],\"position\":\"0\"},\"93\":{\"id\":\"93\",\"code\":\"color\",\"label\":\"Color\",\"options\":[{\"id\":\"49\",\"label\":\"Black\",\"products\":[\"1354\",\"1357\",\"1360\",\"1363\",\"1366\"]},{\"id\":\"50\",\"label\":\"Blue\",\"products\":[\"1355\",\"1358\",\"1361\",\"1364\",\"1367\"]},{\"id\":\"56\",\"label\":\"Orange\",\"products\":[\"1356\",\"1359\",\"1362\",\"1365\",\"1368\"]}],\"position\":\"1\"}},\"template\":\"$<%- data.price %>\",\"currencyFormat\":\"$%s\",\"optionPrices\":{\"1354\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1355\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1356\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1357\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1358\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1359\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1360\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1361\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1362\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1363\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1364\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1365\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1366\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1367\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]},\"1368\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69},\"tierPrices\":[]}},\"priceFormat\":{\"pattern\":\"$%s\",\"precision\":2,\"requiredPrecision\":2,\"decimalSymbol\":\".\",\"group";
  private static final String  magentoNeveJacketHtmlStrPart7 = "" +
  		"Symbol\":\",\",\"groupLength\":3,\"integerRequired\":1},\"prices\":{\"oldPrice\":{\"amount\":69},\"basePrice\":{\"amount\":69},\"finalPrice\":{\"amount\":69}},\"productId\":\"1369\",\"chooseText\":\"Choose an Option...\",\"images\":{\"1354\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1355\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c68" +
  		"7aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/me" +
  		"dia\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1356\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1357\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/w" +
  		"j11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1358\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/ca" +
  		"che\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1359\":[{\"thumb\":\"http:\\/\\/e" +
  		"xample.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1360\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\"" +
  		":\"image\",\"videoUrl\":null}],\"1361\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\"," +
  		"\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1362\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe" +
  		"0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1363\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1364\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/" +
  		"catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"ht" +
  		"tp:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"positio";
  private static final String  magentoNeveJacketHtmlStrPart8 = "" +
  	"n\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1365\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1366\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-black_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-black_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-black_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}],\"1367\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\"1368\":[{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-orange_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-orange_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-orange_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null}]},\"index\":{\"1354\":{\"141\":\"167\",\"93\":\"49\"},\"1355\":{\"141\":\"167\",\"93\":\"50\"},\"1356\":{\"141\":\"167\",\"93\":\"56\"},\"1357\":{\"141\":\"168\",\"93\":\"49\"},\"1358\":{\"141\":\"168\",\"93\":\"50\"},\"1359\":{\"141\":\"168\",\"93\":\"56\"},\"1360\":{\"141\":\"169\",\"93\":\"49\"},\"1361\":{\"141\":\"169\",\"93\":\"50\"},\"1362\":{\"141\":\"169\",\"93\":\"56\"},\"1363\":{\"141\":\"170\",\"93\":\"49\"},\"1364\":{\"141\":\"170\",\"93\":\"50\"},\"1365\":{\"141\":\"170\",\"93\":\"56\"},\"1366\":{\"141\":\"171\",\"93\":\"49\"},\"1367\":{\"141\":\"171\",\"93\":\"50\"},\"1368\":{\"141\":\"171\",\"93\":\"56\"}}}            }\r\n                                        }\r\n                                    }\r\n                                 </script>\r\n                                 <script>\r\n                                    require([\r\n                                        \"jquery\",\r\n                                        \"jquery/ui\"\r\n                                    ], function($){\r\n                                    \r\n                                    //<![CDATA[\r\n                                        $.extend(true, $, {\r\n                                            calendarConfig: {\r\n                                                dayNames: [\"Sunday\",\"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\",\"Saturday\"],\r\n                                                dayNamesMin: [\"Sun\",\"Mon\",\"Tue\",\"Wed\",\"Thu\",\"Fri\",\"Sat\"],\r\n                                                monthNames: [\"January\",\"February\",\"March\",\"April\",\"May\",\"June\",\"July\",\"August\",\"September\",\"October\",\"November\",\"December\"],\r\n                                                monthNamesShort: [\"Jan\",\"Feb\",\"Mar\",\"Apr\",\"May\",\"Jun\",\"Jul\",\"Aug\",\"Sep\",\"Oct\",\"Nov\",\"Dec\"],\r\n                                                infoTitle: \"About the calendar\",\r\n                                                firstDay: 0,\r\n                                                closeText: \"Close\",\r\n                                                currentText: \"Go Today\",\r\n                                                prevText: \"Previous\",\r\n                                                nextText: \"Next\",\r\n                                                weekHeader: \"WK\",\r\n                                                timeText: \"Time\",\r\n                                                hourText: \"Hour\",\r\n                                                minuteText: \"Minute\",\r\n                                                dateFormat: $.datepicker.RFC_2822,\r\n                                                showOn: \"button\",\r\n                                                showAnim: \"\",\r\n                                                changeMonth: true,\r\n                                                changeYear: true,\r\n                                                buttonImageOnly: null,\r\n                                                buttonImage: null,\r\n                                                showButtonPanel: true,\r\n                                                showWeek: true,\r\n                                                timeFormat: \'\',\r\n                                                showTime: false,\r\n                                                showHour: false,\r\n                                                showMinute: false\r\n                                            }\r\n                                        });\r\n                                    \r\n                                        enUS = {\"m\":{\"wide\":[\"January\",\"February\",\"March\",\"April\",\"May\",\"June\",\"July\",\"August\",\"September\",\"October\",\"November\",\"December\"],\"abbr\":[\"Jan\",\"Feb\",\"Mar\",\"Apr\",\"May\",\"Jun\",\"Jul\",\"Aug\",\"Sep\",\"Oct\",\"Nov\",\"Dec\"]}}; // en_US locale reference\r\n                                    //]]>\r\n                                    \r\n                                    });\r\n                                 </script>\r\n                              </div>\r\n                           </div>\r\n                           <div class=\"product-options-bottom\">\r\n                              <div class=\"box-tocart\">\r\n                                 <div class=\"fieldset\">\r\n                                    <div class=\"field qty\">\r\n                                       <label class=\"label\" for=\"qty\"><span>Qty</span></label>\r\n                                       <div class=\"control\">\r\n                                          <input type=\"number\"\r\n                                             name=\"qty\"\r\n                                             id=\"qty\"\r\n                                             value=\"1\"\r\n                                             title=\"Qty\"\r\n                                             class=\"input-text qty\"\r\n                                             data-validate=\"{&quot;required-number&quot;:true,&quot;validate-item-quantity&quot;:{&quot;minAllowed&quot;:1}}\"\r\n                                             />\r\n                                       </div>\r\n                                    </div>\r\n                                    <div class=\"actions\">\r\n                                       <button type=\"submit\"\r\n                                          title=\"Add to Cart\"\r\n                                          class=\"action primary tocart\"\r\n                                          id=\"product-addtocart-button\">\r\n                                       <span>Add to Cart</span>\r\n                                       </button>\r\n                                       <div id=\"instant-purchase\" data-bind=\"scope:\'instant-purchase\'\">\r\n                                          <!-- ko template: getTemplate() --><!-- /ko -->\r\n                                       </div>\r\n                                       <script type=\"text/x-magento-init\">\r\n                                          {\r\n                                              \"#instant-purchase\": {\r\n                                                  \"Magento_Ui/js/core/app\": {\"components\":{\"instant-purchase\":{\"component\":\"Magento_InstantPurchase\\/js\\/view\\/instant-purchase\",\"config\":{\"template\":\"Magento_InstantPurchase\\/instant-purchase\",\"buttonText\":\"Instant Purchase\",\"purchaseUrl\":\"http:\\/\\/example.com\\/instantpurchase\\/button\\/placeOrder\\/\"}}}}        }\r\n                                          }\r\n                                       </script>\r\n                                    </div>\r\n                                 </div>\r\n            ";
  private static final String  magentoNeveJacketHtmlStrPart9 = "" +
  	"                  </div>\r\n                              <script type=\"text/x-magento-init\">\r\n                                 {\r\n                                     \"#product_addtocart_form\": {\r\n                                         \"Magento_Catalog/js/validate-product\": {}\r\n                                     }\r\n                                 }\r\n                              </script>\r\n                           </div>\r\n                        </form>\r\n                     </div>\r\n                     <script>\r\n                        require([\r\n                            \'jquery\',\r\n                            \'priceBox\'\r\n                        ], function($){\r\n                            var dataPriceBoxSelector = \'[data-role=priceBox]\',\r\n                                dataProductIdSelector = \'[data-product-id=1369]\',\r\n                                priceBoxes = $(dataPriceBoxSelector + dataProductIdSelector);\r\n                        \r\n                            priceBoxes = priceBoxes.filter(function(index, elem){\r\n                                return !$(elem).find(\'.price-from\').length;\r\n                            });\r\n                        \r\n                            priceBoxes.priceBox({\'priceConfig\': {\"productId\":\"1369\",\"priceFormat\":{\"pattern\":\"$%s\",\"precision\":2,\"requiredPrecision\":2,\"decimalSymbol\":\".\",\"groupSymbol\":\",\",\"groupLength\":3,\"integerRequired\":1},\"prices\":{\"oldPrice\":{\"amount\":69,\"adjustments\":[]},\"basePrice\":{\"amount\":69,\"adjustments\":[]},\"finalPrice\":{\"amount\":69,\"adjustments\":[]}},\"idSuffix\":\"_clone\",\"tierPrices\":[],\"calculationAlgorithm\":\"TOTAL_BASE_CALCULATION\"}});\r\n                        });\r\n                     </script>\r\n                     <div class=\"product-social-links\">\r\n                        <div class=\"product-addto-links\" data-role=\"add-to-links\">\r\n                           <a href=\"#\"\r\n                              class=\"action towishlist\"\r\n                              data-post=\'{\"action\":\"http:\\/\\/example.com\\/wishlist\\/index\\/add\\/\",\"data\":{\"product\":\"1369\",\"uenc\":\"aHR0cDovL2V4YW1wbGUuY29tL25ldmUtc3R1ZGlvLWRhbmNlLWphY2tldC5odG1s\"}}\'\r\n                              data-action=\"add-to-wishlist\"><span>Add to Wish List</span></a>\r\n                           <script type=\"text/x-magento-init\">\r\n                              {\r\n                                  \"body\": {\r\n                                      \"addToWishlist\": {\"productType\":\"configurable\"}        }\r\n                              }\r\n                           </script>\r\n                           <a href=\"#\" data-post=\'{\"action\":\"http:\\/\\/example.com\\/catalog\\/product_compare\\/add\\/\",\"data\":{\"product\":\"1369\",\"uenc\":\"aHR0cDovL2V4YW1wbGUuY29tL25ldmUtc3R1ZGlvLWRhbmNlLWphY2tldC5odG1s\"}}\'\r\n                              data-role=\"add-to-links\"\r\n                              class=\"action tocompare\"><span>Add to Compare</span></a>\r\n                        </div>\r\n                        <a href=\"http://example.com/sendfriend/product/send/id/1369/cat_id/23/\"\r\n                           class=\"action mailto friend\"><span>Email</span></a>\r\n                     </div>\r\n                  </div>\r\n                  <div class=\"product media\">\r\n                     <a id=\"gallery-prev-area\" tabindex=\"-1\"></a>\r\n                     <div class=\"action-skip-wrapper\"><a class=\"action skip gallery-next-area\" href=\"#gallery-next-area\"><span>Skip to the end of the images gallery</span></a></div>\r\n                     <div class=\"gallery-placeholder _block-content-loading\" data-gallery-role=\"gallery-placeholder\">\r\n                        <div data-role=\"loader\" class=\"loading-mask\">\r\n                           <div class=\"loader\">\r\n                              <img src=\"http://example.com/pub/static/version1524101200/frontend/Magento/luma/en_US/images/loader-1.gif\"\r\n                                 alt=\"Loading...\">\r\n                           </div>\r\n                        </div>\r\n                     </div>\r\n                     <!--Fix for jumping content. Loader must be the same size as gallery.-->\r\n                     <script>\r\n                        var config = {\r\n                                \"width\": 700,\r\n                                \"thumbheight\": 110,\r\n                                \"navtype\": \"slides\",\r\n                                \"height\": 560        },\r\n                            thumbBarHeight = 0,\r\n                            loader = document.querySelectorAll(\'[data-gallery-role=\"gallery-placeholder\"] [data-role=\"loader\"]\')[0];\r\n                        \r\n                        if (config.navtype === \'horizontal\') {\r\n                            thumbBarHeight = config.thumbheight;\r\n                        }\r\n                        \r\n                        loader.style.paddingBottom = ( config.height / config.width * 100) + \"%\";\r\n                     </script>\r\n                     <script type=\"text/x-magento-init\">\r\n                        {\r\n                            \"[data-gallery-role=gallery-placeholder]\": {\r\n                                \"mage/gallery/gallery\": {\r\n                                    \"mixins\":[\"magnifier/magnify\"],\r\n                                    \"magnifierOpts\": {\"fullscreenzoom\":\"20\",\"top\":\"\",\"left\":\"\",\"width\":\"\",\"height\":\"\",\"eventType\":\"hover\",\"enabled\":\"false\"},\r\n                                    \"data\": [{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_main.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_main.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_main.jpg\",\"caption\":\"\",\"position\":\"1\",\"isMain\":true,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_alt1.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_alt1.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_alt1.jpg\",\"caption\":\"\",\"position\":\"2\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null},{\"thumb\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f9c7fbe9b524c081a3ccf800cbd963eb\\/w\\/j\\/wj11-blue_back.jpg\",\"img\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/c687aa7517cf01e65c009f6943c2b1e9\\/w\\/j\\/wj11-blue_back.jpg\",\"full\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/926507dc7f93631a094422215b778fe0\\/w\\/j\\/wj11-blue_back.jpg\",\"caption\":\"\",\"position\":\"3\",\"isMain\":false,\"type\":\"image\",\"videoUrl\":null}],\r\n                                    \"options\": {\r\n                                        \"nav\": \"thumbs\",\r\n                                                                \"loop\": true,\r\n                                                                                    \"keyboard\": true,\r\n                                                                                    \"arrows\": true,\r\n                                                                                    \"allowfullscreen\": true,\r\n                                                                                    \"showCaption\": false,\r\n                                                            \"width\": \"700\",\r\n                                        \"thumbwidth\": \"88\",\r\n                                                                \"thumbheight\": 110,\r\n                                                                                    \"height\": 560,\r\n                                                                                    \"transitionduration\": 500,\r\n                                                            \"transition\": \"slide\",\r\n                                                                \"navarrows\": true,\r\n                                                            \"navtype\": \"slides\",\r\n                                        \"navdir\": \"horizontal\"\r\n                                    },\r\n                                    \"fullscreen\": {\r\n                                        \"nav\": \"thumbs\",\r\n                                                                \"loop\": true,\r\n                                                            \"navdir\": \"horizontal\",\r\n                                                            \"navtype\": \"slides\",\r\n                                                                \"arrows\": true,\r\n                                                                                    \"showCaption\": false,\r\n                                                                                    \"transitionduration\": 500,\r\n                                                            \"transition\": \"slide\"\r\n                                    },\r\n                                    \"breakpoints\": {\"mobile\":{\"conditions\":{\"max-width\":\"767px\"},\"options\":{\"options\":{\"nav\":\"dots\"}}}}            }\r\n                            }\r\n                        }\r\n                     </script>\r\n                     <script type=\"text/x-magento-init\">\r\n                        {\r\n                            \"[data-gallery-role=gallery-placeholder]\": {\r\n                                \"Magento_ProductVideo/js/fotorama-add-vide";
  private static final String  magentoNeveJacketHtmlStrPart10 = "" +
  	"o-events\": {\r\n                                    \"videoData\": [{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false}],\r\n                                    \"videoSettings\": [{\"playIfBase\":\"0\",\"showRelated\":\"0\",\"videoAutoRestart\":\"0\"}],\r\n                                    \"optionsVideoData\": {\"1354\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1355\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false}],\"1356\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1357\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1358\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false}],\"1359\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1360\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1361\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false}],\"1362\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1363\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1364\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false}],\"1365\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1366\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}],\"1367\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false},{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":false}],\"1368\":[{\"mediaType\":\"image\",\"videoUrl\":null,\"isBase\":true}]}            }\r\n                            }\r\n                        }\r\n                     </script>\r\n                     <div class=\"action-skip-wrapper\"><a class=\"action skip gallery-prev-area\" href=\"#gallery-prev-area\"><span>Skip to the beginning of the images gallery</span></a></div>\r\n                     <a id=\"gallery-next-area\" tabindex=\"-1\"></a>\r\n                  </div>\r\n                  <div class=\"product info detailed\">\r\n                     <div class=\"product data items\" data-mage-init=\'{\"tabs\":{\"openedState\":\"active\"}}\'>\r\n                        <div class=\"data item title\"\r\n                           aria-labeledby=\"tab-label-product.info.description-title\"\r\n                           data-role=\"collapsible\" id=\"tab-label-product.info.description\">\r\n                           <a class=\"data switch\"\r\n                              tabindex=\"-1\"\r\n                              data-toggle=\"switch\"\r\n                              href=\"#product.info.description\"\r\n                              id=\"tab-label-product.info.description-title\">\r\n                           Details                    </a>\r\n                        </div>\r\n                        <div class=\"data item content\" id=\"product.info.description\" data-role=\"content\">\r\n                           <div class=\"product attribute description\">\r\n                              <div class=\"value\" >\r\n                                 <p>If you\'re constantly on the move, the Neve Studio Dance Jacket is for you. It\'s not just for dance, either, with a tight fit that works as a mid-layer. The reversible design makes it even more versatile.</p>\r\n                                 <p>&bull; Bright blue 1/4 zip pullover.<br />&bull; CoolTech&trade; liner is sweat-wicking.<br />&bull; Sleeve thumbholes.<br />&bull; Zipper garage to protect your chin.<br />&bull; Stretchy collar drawcords.</p>\r\n                              </div>\r\n                           </div>\r\n                        </div>\r\n                        <div class=\"data item title\"\r\n                           aria-labeledby=\"tab-label-additional-title\"\r\n                           data-role=\"collapsible\" id=\"tab-label-additional\">\r\n                           <a class=\"data switch\"\r\n                              tabindex=\"-1\"\r\n                              data-toggle=\"switch\"\r\n                              href=\"#additional\"\r\n                              id=\"tab-label-additional-title\">\r\n                           More Information                    </a>\r\n                        </div>\r\n                        <div class=\"data item content\" id=\"additional\" data-role=\"content\">\r\n                           <div class=\"additional-attributes-wrapper table-wrapper\">\r\n                              <table class=\"data table additional-attributes\" id=\"product-attribute-specs-table\">\r\n                                 <caption class=\"table-caption\">More Information</caption>\r\n                                 <tbody>\r\n                                    <tr>\r\n                                       <th class=\"col label\" scope=\"row\">Activity</th>\r\n                                       <td class=\"col data\" data-th=\"Activity\">N/A</td>\r\n                                    </tr>\r\n                                    <tr>\r\n                                       <th class=\"col label\" scope=\"row\">Style</th>\r\n                                       <td class=\"col data\" data-th=\"Style\">Jacket, Lightweight, Hooded, Soft Shell, &frac14; zip, Reversible, Pullover</td>\r\n                                    </tr>\r\n                                    <tr>\r\n                                       <th class=\"col label\" scope=\"row\">Material</th>\r\n                                       <td class=\"col data\" data-th=\"Material\">Mesh, Lycra&reg;, Nylon, CoolTech&trade;</td>\r\n                                    </tr>\r\n                                    <tr>\r\n                                       <th class=\"col label\" scope=\"row\">Sleeve</th>\r\n                                       <td class=\"col data\" data-th=\"Sleeve\">N/A</td>\r\n                                    </tr>\r\n                                    <tr>\r\n                                       <th class=\"col label\" scope=\"row\">Collar</th>\r\n                                       <td class=\"col data\" data-th=\"Collar\">N/A</td>\r\n                                    </tr>\r\n                                    <tr>\r\n                                       <th class=\"col label\" scope=\"row\">Pattern</th>\r\n                                       <td class=\"col data\" data-th=\"Pattern\">Solid</td>\r\n                                    </tr>\r\n                                    <tr>\r\n                                       <th class=\"col label\" scope=\"row\">Climate</th>\r\n                                       <td class=\"col data\" data-th=\"Climate\">Indoor, Mild, Spring</td>\r\n                                    </tr>\r\n                                 </tbody>\r\n                              </table>\r\n                           </div>\r\n                        </div>\r\n                        <div class=\"data item title\"\r\n                           aria-labeledby=\"tab-label-reviews-title\"\r\n                           data-role=\"collapsible\" id=\"tab-label-reviews\">\r\n                           <a class=\"data switch\"\r\n                              tabindex=\"-1\"\r\n                              data-toggle=\"switch\"\r\n                              href=\"#reviews\"\r\n                              id=\"tab-label-reviews-title\">\r\n                           Reviews <span class=\"counter\">3</span>                    </a>\r\n                        </div>\r\n                        <div class=\"data item content\" id=\"reviews\" data-role=\"content\">\r\n                           <div id=\"product-review-container\" data-role=\"product-review\"></div>\r\n                           <div class=\"block review-add\">\r\n                              <div class=\"block-title\"><strong>Write Your Own Review</strong></div>\r\n                              <div class=\"block-content\">\r\n                                 <form action=\"http://example.com/review/product/post/id/1369/\" class=\"review-form\" method=\"post\" id=\"review-form\" data-role=\"product-review-form\" data-bind=\"scope: \'review-form\'\">\r\n                                    <input name=\"form_key\" type=\"hidden\" value=\"YxQj7rnvUlzGxMwS\" />        \r\n                                    <fieldset class=\"fieldset review-fieldset\" data-hasrequired=\"&#x2A;&#x20;Required&#x20;Fields\">\r\n                                       <legend class=\"legend review-legend\"><span>You&#039;re reviewing:</span><strong>Neve Studio Dance Jacket</strong></legend>\r\n                                       <br />\r\n                                       <span id=\"input-message-box\"></span>\r\n                                       <fieldset class=\"field required review-field-ratings\">\r\n                                          <legend class=\"label\"><span>Your Rating</span></legend>\r\n                                          <br/>\r\n                                          <div class=\"control\">\r\n                                             <div class=\"nested\" id=\"product-review-table\">\r\n                                                <div class=\"field choice review-field-rating\">\r\n                                                   <label class=\"label\" id=\"Rating_rating_label\"><span>Rating</span></label>\r\n                                                   <div class=\"control review-control-vote\">\r\n                                                      <input\r\n       ";
  private static final String  magentoNeveJacketHtmlStrPart11 = "" +
  	"                                                  type=\"radio\"\r\n                                                         name=\"ratings[4]\"\r\n                                                         id=\"Rating_1\"\r\n                                                         value=\"16\"\r\n                                                         class=\"radio\"\r\n                                                         data-validate=\"{ \'rating-required\':true}\"\r\n                                                         aria-labelledby=\"Rating_rating_label Rating_1_label\" />\r\n                                                      <label\r\n                                                         class=\"rating-1\"\r\n                                                         for=\"Rating_1\"\r\n                                                         title=\"1&#x20;star\"\r\n                                                         id=\"Rating_1_label\">\r\n                                                      <span>1 star</span>\r\n                                                      </label>\r\n                                                      <input\r\n                                                         type=\"radio\"\r\n                                                         name=\"ratings[4]\"\r\n                                                         id=\"Rating_2\"\r\n                                                         value=\"17\"\r\n                                                         class=\"radio\"\r\n                                                         data-validate=\"{ \'rating-required\':true}\"\r\n                                                         aria-labelledby=\"Rating_rating_label Rating_2_label\" />\r\n                                                      <label\r\n                                                         class=\"rating-2\"\r\n                                                         for=\"Rating_2\"\r\n                                                         title=\"2&#x20;stars\"\r\n                                                         id=\"Rating_2_label\">\r\n                                                      <span>2 stars</span>\r\n                                                      </label>\r\n                                                      <input\r\n                                                         type=\"radio\"\r\n                                                         name=\"ratings[4]\"\r\n                                                         id=\"Rating_3\"\r\n                                                         value=\"18\"\r\n                                                         class=\"radio\"\r\n                                                         data-validate=\"{ \'rating-required\':true}\"\r\n                                                         aria-labelledby=\"Rating_rating_label Rating_3_label\" />\r\n                                                      <label\r\n                                                         class=\"rating-3\"\r\n                                                         for=\"Rating_3\"\r\n                                                         title=\"3&#x20;stars\"\r\n                                                         id=\"Rating_3_label\">\r\n                                                      <span>3 stars</span>\r\n                                                      </label>\r\n                                                      <input\r\n                                                         type=\"radio\"\r\n                                                         name=\"ratings[4]\"\r\n                                                         id=\"Rating_4\"\r\n                                                         value=\"19\"\r\n                                                         class=\"radio\"\r\n                                                         data-validate=\"{ \'rating-required\':true}\"\r\n                                                         aria-labelledby=\"Rating_rating_label Rating_4_label\" />\r\n                                                      <label\r\n                                                         class=\"rating-4\"\r\n                                                         for=\"Rating_4\"\r\n                                                         title=\"4&#x20;stars\"\r\n                                                         id=\"Rating_4_label\">\r\n                                                      <span>4 stars</span>\r\n                                                      </label>\r\n                                                      <input\r\n                                                         type=\"radio\"\r\n                                                         name=\"ratings[4]\"\r\n                                                         id=\"Rating_5\"\r\n                                                         value=\"20\"\r\n                                                         class=\"radio\"\r\n                                                         data-validate=\"{ \'rating-required\':true}\"\r\n                                                         aria-labelledby=\"Rating_rating_label Rating_5_label\" />\r\n                                                      <label\r\n                                                         class=\"rating-5\"\r\n                                                         for=\"Rating_5\"\r\n                                                         title=\"5&#x20;stars\"\r\n                                                         id=\"Rating_5_label\">\r\n                                                      <span>5 stars</span>\r\n                                                      </label>\r\n                                                   </div>\r\n                                                </div>\r\n                                             </div>\r\n                                             <input type=\"hidden\" name=\"validate_rating\" class=\"validate-rating\" value=\"\" />\r\n                                          </div>\r\n                                       </fieldset>\r\n                                       <div class=\"field review-field-nickname required\">\r\n                                          <label for=\"nickname_field\" class=\"label\"><span>Nickname</span></label>\r\n                                          <div class=\"control\">\r\n                                             <input type=\"text\" name=\"nickname\" id=\"nickname_field\" class=\"input-text\" data-validate=\"{required:true}\" data-bind=\"value: nickname()\" />\r\n                                          </div>\r\n                                       </div>\r\n                                       <div class=\"field review-field-summary required\">\r\n                                          <label for=\"summary_field\" class=\"label\"><span>Summary</span></label>\r\n                                          <div class=\"control\">\r\n                                             <input type=\"text\" name=\"title\" id=\"summary_field\" class=\"input-text\" data-validate=\"{required:true}\" data-bind=\"value: review().title\" />\r\n                                          </div>\r\n                                       </div>\r\n                                       <div class=\"field review-field-text required\">\r\n                                          <label for=\"review_field\" class=\"label\"><span>Review</span></label>\r\n                                          <div class=\"control\">\r\n                                             <textarea name=\"detail\" id=\"review_field\" cols=\"5\" rows=\"3\" data-validate=\"{required:true}\" data-bind=\"value: review().detail\"></textarea>\r\n                                          </div>\r\n                                       </div>\r\n                                    </fieldset>\r\n                                    <div class=\"actions-toolbar review-form-actions\">\r\n                                       <div class=\"primary actions-primary\">\r\n                                          <button type=\"submit\" class=\"action submit primary\"><span>Submit Review</span></button>\r\n                                       </div>\r\n                                    </div>\r\n                                 </form>\r\n                                 <script type=\"text/x-magento-init\">\r\n                                    {\r\n                                        \"[data-role=product-review-form]\": {\r\n                                            \"Magento_Ui/js/core/app\": {\"components\":{\"review-form\":{\"component\":\"Magento_Review\\/js\\/view\\/review\"}}}    },\r\n                                        \"#review-form\": {\r\n                                            \"Magento_Review/js/error-placement\": {},\r\n                                            \"Magento_Review/js/validate-review\": {}\r\n                                        }\r\n                                    }\r\n                                 </script>\r\n                              </div>\r\n                           </div>\r\n                           <script type=\"text/x-magento-init\">\r\n                              {\r\n                                  \"*\": {\r\n                                      \"Magento_Review/js/process-reviews\": {\r\n                                          \"productReviewUrl\": \"http\\u003A\\u002F\\u002Fexample.com\\u002Freview\\u002Fproduct\\u002FlistAjax\\u002Fid\\u002F1369\\u002F\",\r\n                                          \"reviewsTabSelector\": \"#tab-label-reviews\"\r\n                                      }\r\n                                  }\r\n                              }\r\n                           </script>\r\n                        </div>\r\n                     </div>\r\n                  </div>\r\n ";
  private static final String  magentoNeveJacketHtmlStrPart12 = "" +
  	"                 <input name=\"form_key\" type=\"hidden\" value=\"YxQj7rnvUlzGxMwS\" />\r\n                  <div id=\"authenticationPopup\" data-bind=\"scope:\'authenticationPopup\'\" style=\"display: none;\">\r\n                     <script>\r\n                        window.authenticationPopup = {\"autocomplete\":\"off\",\"customerRegisterUrl\":\"http:\\/\\/example.com\\/customer\\/account\\/create\\/\",\"customerForgotPasswordUrl\":\"http:\\/\\/example.com\\/customer\\/account\\/forgotpassword\\/\",\"baseUrl\":\"http:\\/\\/example.com\\/\"};\r\n                     </script>\r\n                     <!-- ko template: getTemplate() --><!-- /ko -->\r\n                     <script type=\"text/x-magento-init\">\r\n                        {\r\n                            \"#authenticationPopup\": {\r\n                                \"Magento_Ui/js/core/app\": {\"components\":{\"authenticationPopup\":{\"component\":\"Magento_Customer\\/js\\/view\\/authentication-popup\",\"children\":{\"messages\":{\"component\":\"Magento_Ui\\/js\\/view\\/messages\",\"displayArea\":\"messages\"},\"captcha\":{\"component\":\"Magento_Captcha\\/js\\/view\\/checkout\\/loginCaptcha\",\"displayArea\":\"additional-login-form-fields\",\"formId\":\"user_login\",\"configSource\":\"checkout\"}}}}}            },\r\n                            \"*\": {\r\n                                \"Magento_Ui/js/block-loader\": \"http\\u003A\\u002F\\u002Fexample.com\\u002Fpub\\u002Fstatic\\u002Fversion1524101200\\u002Ffrontend\\u002FMagento\\u002Fluma\\u002Fen_US\\u002Fimages\\u002Floader\\u002D1.gif\"\r\n                            }\r\n                        }\r\n                     </script>\r\n                  </div>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\"*\":{\"Magento_Customer\\/js\\/section-config\":{\"sections\":{\"stores\\/store\\/switch\":\"*\",\"directory\\/currency\\/switch\":\"*\",\"*\":[\"messages\"],\"customer\\/account\\/logout\":[\"recently_viewed_product\",\"recently_compared_product\"],\"customer\\/account\\/loginpost\":\"*\",\"customer\\/account\\/createpost\":\"*\",\"customer\\/account\\/editpost\":\"*\",\"customer\\/ajax\\/login\":[\"checkout-data\",\"cart\"],\"catalog\\/product_compare\\/add\":[\"compare-products\"],\"catalog\\/product_compare\\/remove\":[\"compare-products\"],\"catalog\\/product_compare\\/clear\":[\"compare-products\"],\"sales\\/guest\\/reorder\":[\"cart\"],\"sales\\/order\\/reorder\":[\"cart\"],\"checkout\\/cart\\/add\":[\"cart\"],\"checkout\\/cart\\/delete\":[\"cart\"],\"checkout\\/cart\\/updatepost\":[\"cart\"],\"checkout\\/cart\\/updateitemoptions\":[\"cart\"],\"checkout\\/cart\\/couponpost\":[\"cart\"],\"checkout\\/cart\\/estimatepost\":[\"cart\"],\"checkout\\/cart\\/estimateupdatepost\":[\"cart\"],\"checkout\\/onepage\\/saveorder\":[\"cart\",\"checkout-data\",\"last-ordered-items\",\"checkout-fields\"],\"checkout\\/sidebar\\/removeitem\":[\"cart\"],\"checkout\\/sidebar\\/updateitemqty\":[\"cart\"],\"rest\\/*\\/v1\\/carts\\/*\\/payment-information\":[\"cart\",\"checkout-data\",\"last-ordered-items\",\"instant-purchase\"],\"rest\\/*\\/v1\\/guest-carts\\/*\\/payment-information\":[\"cart\",\"checkout-data\"],\"rest\\/*\\/v1\\/guest-carts\\/*\\/selected-payment-method\":[\"cart\",\"checkout-data\"],\"rest\\/*\\/v1\\/carts\\/*\\/selected-payment-method\":[\"cart\",\"checkout-data\",\"instant-purchase\"],\"customer\\/address\\/*\":[\"instant-purchase\"],\"customer\\/account\\/*\":[\"instant-purchase\"],\"vault\\/cards\\/deleteaction\":[\"instant-purchase\"],\"multishipping\\/checkout\\/overviewpost\":[\"cart\"],\"paypal\\/express\\/placeorder\":[\"cart\",\"checkout-data\"],\"paypal\\/payflowexpress\\/placeorder\":[\"cart\",\"checkout-data\"],\"review\\/product\\/post\":[\"review\"],\"wishlist\\/index\\/add\":[\"wishlist\"],\"wishlist\\/index\\/remove\":[\"wishlist\"],\"wishlist\\/index\\/updateitemoptions\":[\"wishlist\"],\"wishlist\\/index\\/update\":[\"wishlist\"],\"wishlist\\/index\\/cart\":[\"wishlist\",\"cart\"],\"wishlist\\/index\\/fromcart\":[\"wishlist\",\"cart\"],\"wishlist\\/index\\/allcart\":[\"wishlist\",\"cart\"],\"wishlist\\/shared\\/allcart\":[\"wishlist\",\"cart\"],\"wishlist\\/shared\\/cart\":[\"cart\"],\"authorizenet\\/directpost_payment\\/place\":[\"cart\",\"checkout-data\"],\"braintree\\/paypal\\/placeorder\":[\"cart\",\"checkout-data\"]},\"clientSideSections\":[\"checkout-data\",\"cart-data\"],\"baseUrls\":[\"http:\\/\\/example.com\\/\"]}}}\r\n                  </script>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\"*\":{\"Magento_Customer\\/js\\/customer-data\":{\"sectionLoadUrl\":\"http:\\/\\/example.com\\/customer\\/section\\/load\\/\",\"expirableSectionLifetime\":60,\"expirableSectionNames\":[\"cart\"],\"cookieLifeTime\":\"3600\",\"updateSessionUrl\":\"http:\\/\\/example.com\\/customer\\/account\\/updateSession\\/\"}}}\r\n                  </script>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\"*\":{\"Magento_Customer\\/js\\/invalidation-processor\":{\"invalidationRules\":{\"website-rule\":{\"Magento_Customer\\/js\\/invalidation-rules\\/website-rule\":{\"scopeConfig\":{\"websiteId\":1}}}}}}}\r\n                  </script>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\r\n                         \"body\": {\r\n                             \"pageCache\": {\"url\":\"http:\\/\\/example.com\\/page_cache\\/block\\/render\\/id\\/1369\\/\",\"handles\":[\"default\",\"catalog_product_view\",\"catalog_product_view_id_1369\",\"catalog_product_view_sku_WJ11\",\"catalog_product_view_type_configurable\"],\"originalRequest\":{\"route\":\"catalog\",\"controller\":\"product\",\"action\":\"view\",\"uri\":\"\\/neve-studio-dance-jacket.html\"},\"versionCookieName\":\"private_content_version\"}        }\r\n                     }\r\n                  </script>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\r\n                         \"body\": {\r\n                             \"requireCookie\": {\"noCookieUrl\":\"http:\\/\\/example.com\\/cookie\\/index\\/noCookies\\/\",\"triggers\":[\".action.towishlist\"]}        }\r\n                     }\r\n                  </script>\r\n                  <script type=\"text/x-magento-init\">\r\n                     {\r\n                         \"*\": {\r\n                                 \"Magento_Catalog/js/product/view/provider\": {\r\n                                     \"data\": {\"items\":{\"1369\":{\"add_to_cart_button\":{\"post_data\":\"{\\\"action\\\":\\\"http:\\\\\\/\\\\\\/example.com\\\\\\/checkout\\\\\\/cart\\\\\\/add\\\\\\/uenc\\\\\\/%25uenc%25\\\\\\/product\\\\\\/1369\\\\\\/\\\",\\\"data\\\":{\\\"product\\\":\\\"1369\\\",\\\"uenc\\\":\\\"%uenc%\\\"}}\",\"url\":\"http:\\/\\/example.com\\/checkout\\/cart\\/add\\/uenc\\/%25uenc%25\\/product\\/1369\\/\",\"required_options\":true},\"add_to_compare_button\":{\"post_data\":null,\"url\":\"{\\\"action\\\":\\\"http:\\\\\\/\\\\\\/example.com\\\\\\/catalog\\\\\\/product_compare\\\\\\/add\\\\\\/\\\",\\\"data\\\":{\\\"product\\\":\\\"1369\\\",\\\"uenc\\\":\\\"aHR0cDovL2V4YW1wbGUuY29tL25ldmUtc3R1ZGlvLWRhbmNlLWphY2tldC5odG1s\\\"}}\",\"required_options\":null},\"price_info\":{\"final_price\":69,\"max_price\":69,\"max_regular_price\":69,\"minimal_regular_price\":69,\"special_price\":null,\"minimal_price\":69,\"regular_price\":69,\"formatted_prices\":{\"final_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"max_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"minimal_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"max_regular_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"minimal_regular_price\":null,\"special_price\":null,\"regular_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\"},\"extension_attributes\":{\"msrp\":{\"msrp_price\":\"<span class=\\\"price\\\">$0.00<\\/span>\",\"is_applicable\":\"\",\"is_shown_price_on_gesture\":\"1\",\"msrp_message\":\"\",\"explanation_message\":\"Our price is lower than the manufacturer&#039;s &quot;minimum advertised price.&quot; As a result, we cannot show you the price in catalog or the product page. <br><br> You have no obligation to purchase the product once you know the price. You can simply remove the item from your cart.\"},\"tax_adjustments\":{\"final_price\":69,\"max_price\":69,\"max_regular_price\":69,\"minimal_regular_price\":69,\"special_price\":69,\"minimal_price\":69,\"regular_price\":69,\"formatted_prices\":{\"final_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"max_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"minimal_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"max_regular_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"minimal_regular_price\":null,\"special_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\",\"regular_price\":\"<span class=\\\"price\\\">$69.00<\\/span>\"}},\"weee_attributes\":[],\"weee_adjustment\":\"<span class=\\\"price\\\">$69.00<\\/span>\"}},\"images\":[{\"url\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f073062f50e48eb0f0998593e568d857\\/w\\/j\\/wj11-blue_main.jpg\",\"code\":\"recently_viewed_products_grid_content_widget\",\"height\":300,\"width\":240,\"label\":\"Neve Studio Dance Jacket\",\"resized_width\":240,\"resized_height\":300},{\"url\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/900f44f0120b35eff596cbeba48e1c0a\\/w\\/j\\/wj11-blue_main.jpg\",\"code\":\"recently_viewed_products_list_content_widget\",\"height\":340,\"width\":270,\"label\":\"Neve Studio Dance Jacket\",\"resized_width\":270,\"resized_height\":340},{\"url\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/15dc7e9ba1a6bafcd505d927c7fcfa03\\/w\\/j\\/wj11-blue_main.jpg\",\"code\":\"recently_viewed_products_images_names_widget\",\"height\":90,\"width\":75,\"label\":\"Neve Studio Dance Jacket\",\"resized_width\":75,\"resized_height\":90},{\"url\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/f073062f50e48eb0f0998593e568d857\\/w\\/j\\/wj11-blue_main.jpg\",\"code\":";
  private static final String  magentoNeveJacketHtmlStrPart13 = "" +
  	"\"recently_compared_products_grid_content_widget\",\"height\":300,\"width\":240,\"label\":\"Neve Studio Dance Jacket\",\"resized_width\":240,\"resized_height\":300},{\"url\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/900f44f0120b35eff596cbeba48e1c0a\\/w\\/j\\/wj11-blue_main.jpg\",\"code\":\"recently_compared_products_list_content_widget\",\"height\":340,\"width\":270,\"label\":\"Neve Studio Dance Jacket\",\"resized_width\":270,\"resized_height\":340},{\"url\":\"http:\\/\\/example.com\\/pub\\/media\\/catalog\\/product\\/cache\\/2b4546e5ba001f3aea4287545d649df0\\/w\\/j\\/wj11-blue_main.jpg\",\"code\":\"recently_compared_products_images_names_widget\",\"height\":90,\"width\":75,\"label\":\"Neve Studio Dance Jacket\",\"resized_width\":75,\"resized_height\":90}],\"url\":\"http:\\/\\/example.com\\/women\\/tops-women\\/jackets-women\\/neve-studio-dance-jacket.html\",\"id\":1369,\"name\":\"Neve Studio Dance Jacket\",\"type\":\"configurable\",\"is_salable\":\"1\",\"store_id\":1,\"currency_code\":\"USD\",\"extension_attributes\":{\"review_html\":\"<div class=\\\"product-reviews-summary short\\\">\\n        <div class=\\\"rating-summary\\\">\\n        <span class=\\\"label\\\"><span>Rating:<\\/span><\\/span>\\n        <div class=\\\"rating-result\\\" title=\\\"87%\\\">\\n            <span style=\\\"width:87%\\\"><span>87%<\\/span><\\/span>\\n        <\\/div>\\n    <\\/div>\\n        <div class=\\\"reviews-actions\\\">\\n        <a class=\\\"action view\\\" href=\\\"http:\\/\\/example.com\\/women\\/tops-women\\/jackets-women\\/neve-studio-dance-jacket.html#reviews\\\">3&nbsp;<span>Reviews<\\/span><\\/a>\\n    <\\/div>\\n<\\/div>\\n\",\"wishlist_button\":{\"post_data\":null,\"url\":\"{\\\"action\\\":\\\"http:\\\\\\/\\\\\\/example.com\\\\\\/wishlist\\\\\\/index\\\\\\/add\\\\\\/\\\",\\\"data\\\":{\\\"product\\\":\\\"1369\\\",\\\"uenc\\\":\\\"aHR0cDovL2V4YW1wbGUuY29tL25ldmUtc3R1ZGlvLWRhbmNlLWphY2tldC5odG1s\\\"}}\",\"required_options\":null}}}},\"store\":\"1\",\"currency\":\"USD\"}            }\r\n                         }\r\n                     }\r\n                  </script>\r\n                  <script data-role=\"msrp-popup-template\" type=\"text/x-magento-template\">\r\n                     <div id=\"map-popup-click-for-price\" class=\"map-popup\">\r\n                         <div class=\"popup-header\">\r\n                             <strong class=\"title\" id=\"map-popup-heading-price\"></strong>\r\n                         </div>\r\n                         <div class=\"popup-content\">\r\n                             <div class=\"map-info-price\" id=\"map-popup-content\">\r\n                                 <div class=\"price-box\">\r\n                                     <div class=\"map-msrp\" id=\"map-popup-msrp-box\">\r\n                                         <span class=\"label\">Price</span>\r\n                                         <span class=\"old-price map-old-price\" id=\"map-popup-msrp\">\r\n                                             <span class=\"price\"></span>\r\n                                         </span>\r\n                                     </div>\r\n                                     <div class=\"map-price\" id=\"map-popup-price-box\">\r\n                                         <span class=\"label\">Actual Price</span>\r\n                                         <span id=\"map-popup-price\" class=\"actual-price\"></span>\r\n                                     </div>\r\n                                 </div>\r\n                                 <form action=\"\" method=\"POST\" id=\"product_addtocart_form_from_popup\" class=\"map-form-addtocart\">\r\n                                     <input type=\"hidden\" name=\"product\" class=\"product_id\" value=\"\" id=\"map-popup-product-id\"/>\r\n                                     <button type=\"button\"\r\n                                             title=\"Add to Cart\"\r\n                                             class=\"action tocart primary\"\r\n                                             id=\"map-popup-button\">\r\n                                         <span>Add to Cart</span>\r\n                                     </button>\r\n                                     <div class=\"additional-addtocart-box\">\r\n                                                             </div>\r\n                                 </form>\r\n                             </div>\r\n                             <div class=\"map-text\" id=\"map-popup-text\">\r\n                                 Our price is lower than the manufacturer&#039;s &quot;minimum advertised price.&quot; As a result, we cannot show you the price in catalog or the product page. <br><br> You have no obligation to purchase the product once you know the price. You can simply remove the item from your cart.            </div>\r\n                         </div>\r\n                     </div>\r\n                  </script>\r\n                  <script data-role=\"msrp-info-template\" type=\"text/x-magento-template\">\r\n                     <div id=\"map-popup-what-this\" class=\"map-popup\">\r\n                         <div class=\"popup-header\">\r\n                             <strong class=\"title\" id=\"map-popup-heading-what-this\"></strong>\r\n                         </div>\r\n                         <div class=\"popup-content\">\r\n                             <div class=\"map-help-text\" id=\"map-popup-text-what-this\">\r\n                                 Our price is lower than the manufacturer&#039;s &quot;minimum advertised price.&quot; As a result, we cannot show you the price in catalog or the product page. <br><br> You have no obligation to purchase the product once you know the price. You can simply remove the item from your cart.            </div>\r\n                         </div>\r\n                     </div>\r\n                  </script>\r\n               </div>\r\n            </div>\r\n         </main>\r\n         <footer class=\"page-footer\">\r\n            <div class=\"footer content\">\r\n               <div class=\"block newsletter\">\r\n                  <div class=\"title\"><strong>Newsletter</strong></div>\r\n                  <div class=\"content\">\r\n                     <form class=\"form subscribe\"\r\n                        novalidate\r\n                        action=\"http://example.com/newsletter/subscriber/new/\"\r\n                        method=\"post\"\r\n                        data-mage-init=\'{\"validation\": {\"errorClass\": \"mage-error\"}}\'\r\n                        id=\"newsletter-validate-detail\">\r\n                        <div class=\"field newsletter\">\r\n                           <label class=\"label\" for=\"newsletter\"><span>Sign Up for Our Newsletter:</span></label>\r\n                           <div class=\"control\">\r\n                              <input name=\"email\" type=\"email\" id=\"newsletter\"\r\n                                 placeholder=\"Enter&#x20;your&#x20;email&#x20;address\"\r\n                                 data-validate=\"{required:true, \'validate-email\':true}\"/>\r\n                           </div>\r\n                        </div>\r\n                        <div class=\"actions\">\r\n                           <button class=\"action subscribe primary\" title=\"Subscribe\" type=\"submit\">\r\n                           <span>Subscribe</span>\r\n                           </button>\r\n                        </div>\r\n                     </form>\r\n                  </div>\r\n               </div>\r\n               <div class=\"links\">\r\n                  <div class=\"widget block block-static-block\">\r\n                     <ul class=\"footer links\">\r\n                        <li class=\"nav item\"><a href=\"http://example.com/about-us/\">About us</a></li>\r\n                        <li class=\"nav item\"><a href=\"http://example.com/customer-service/\">Customer Service</a></li>\r\n                     </ul>\r\n                  </div>\r\n               </div>\r\n               <ul class=\"footer links\">\r\n                  <li class=\"nav item\"><a href=\"http://example.com/privacy-policy-cookie-restriction-mode/\">Privacy and Cookie Policy</a></li>\r\n                  <li class=\"nav item\"><a href=\"http://example.com/search/term/popular/\">Search Terms</a></li>\r\n                  <li class=\"nav item\"><a href=\"http://example.com/sales/guest/form/\">Orders and Returns</a></li>\r\n                  <li class=\"nav item\"><a href=\"http://example.com/catalogsearch/advanced/\" data-action=\"advanced-search\">Advanced Search</a></li>\r\n                  <li class=\"nav item\"><a href=\"http://example.com/contact/\">Contact Us</a></li>\r\n               </ul>\r\n            </div>\r\n         </footer>\r\n         <script type=\"text/x-magento-init\">\r\n            {\r\n                \"*\": {\r\n                    \"Magento_Ui/js/core/app\": {\r\n                        \"components\": {\r\n                            \"storage-manager\": {\r\n                                \"component\": \"Magento_Catalog/js/storage-manager\",\r\n                                \"appendTo\": \"\",\r\n                                \"storagesConfiguration\" :\r\n                                             {\"recently_viewed_product\":{\"requestConfig\":{\"syncUrl\":\"http:\\/\\/example.com\\/catalog\\/product\\/frontend_action_synchronize\\/\"},\"lifetime\":\"1000\",\"allowToSendRequest\":null},\"recently_compared_product\":{\"requestConfig\":{\"syncUrl\":\"http:\\/\\/example.com\\/catalog\\/product\\/frontend_action_synchronize\\/\"},\"lifetime\":\"1000\",\"allowToSendRequest\":null},\"product_data_storage\":{\"updateRequestConfig\":{\"url\":\"http:\\/\\/example.com\\/rest\\/default\\/V1\\/products-render-info\"},\"allowToSendRequest\":null}}                        }\r\n                        }\r\n                    }\r\n                }\r\n            }\r\n         </script>\r\n         <script type=\"text/x-magento-init\">\r\n            {\r\n                \"*\": {\r\n                    \"Dotdi";
  private static final String  magentoNeveJacketHtmlStrPart14 = "" +
    "gitalgroup_Email/js/emailCapture\":{\r\n                        \"isEnabled\":\"\",\r\n                        \"type\":\"newsletter\",\r\n                        \"url\":\"http://example.com/connector/ajax/emailcapture/\"\r\n                    }\r\n                }\r\n            }\r\n         </script><small class=\"copyright\">\r\n         <span>Copyright ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¯ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¿ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ½ 2013-2017 Magento, Inc. All rights reserved.</span>\r\n         </small>\r\n      </div>\r\n   </body>\r\n</html>\r\n";
  /* This is the test data used to test (with Junit5) HDLmCurlApache and
     HDLmCurlJetty. This test data is fixed so that the tests yield
     reproducible results. The test data was obtained from a live system,
     so that it is quite complex and useful. Note that this test data is
     used to update a non-production table, not a production table. */
  protected static final String  jsonPostStr = "{\"data\":[{\"id\":\"9ba9c32853744fdfb0d74dd2f2e6833e\",\"company\":\"example.com\",\"division\":\"example.com\",\"site\":\"example.com\",\"enabled\":true,\"mods\":[{\"tooltip\":\"Top node of the node tree\",\"type\":\"top\",\"children\":[{\"tooltip\":\"Company node\",\"type\":\"company\",\"children\":[{\"tooltip\":\"Division node\",\"type\":\"division\",\"children\":[{\"tooltip\":\"Site node\",\"type\":\"site\",\"children\":[{\"tooltip\":\"Title modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Add to Cart Text\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"button\",\"attribute\":\"id\",\"value\":\"product-addtocart-button\"}],\"enabled\":true,\"parameter\":9,\"type\":\"title\",\"titles\":[\"ADD TO CART\",\"Add to ala-carte\"],\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Add to Cart Text\"]},{\"tooltip\":\"Font color modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Font Color\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"span\",\"attribute\":\"itemprop\",\"value\":\"name\"}],\"enabled\":true,\"parameter\":1,\"type\":\"fontcolor\",\"colors\":[\"#000000\",\"#0000ff\",\"#00ff00\",\"#ff0000\",\"#ff0000\"],\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Font Color\"]},{\"tooltip\":\"Font family modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Font Family\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"span\",\"attribute\":\"itemprop\",\"value\":\"name\"}],\"enabled\":true,\"parameter\":3,\"type\":\"fontfamily\",\"families\":[\"arial\",\"arial\",\"cursive\"],\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Font Family\"]},{\"tooltip\":\"Font kerning modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Font Kerning\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"span\",\"attribute\":\"itemprop\",\"value\":\"name\"}],\"enabled\":true,\"parameter\":4,\"type\":\"fontkerning\",\"kernings\":[\"auto\",\"none\",\"normal\"],\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Font Kerning\"]},{\"tooltip\":\"Font size modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Font Size\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"span\",\"attribute\":\"itemprop\",\"value\":\"name\"}],\"enabled\":true,\"parameter\":0,\"type\":\"fontsize\",\"sizebase\":70,\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Font Size\"]},{\"tooltip\":\"Font style modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Font Style\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"span\",\"attribute\":\"itemprop\",\"value\":\"name\"}],\"enabled\":true,\"parameter\":5,\"type\":\"fontstyle\",\"styles\":[\"normal\",\"italic\",\"oblique\"],\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Font Style\"]},{\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Font Text\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[],\"enabled\":true,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Not so cute\"],\"updated\":false,\"xpath\":\"/html/body/div[1]/main/div[2]/div[1]/div[1]/div[1]/h1/span\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Font Text\"]},{\"tooltip\":\"Font weight modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Font Weight\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"span\",\"attribute\":\"itemprop\",\"value\":\"name\"}],\"enabled\":true,\"parameter\":6,\"type\":\"fontweight\",\"weights\":[\"normal\",\"bold\",\"lighter\"],\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Font Weight\"]},{\"tooltip\":\"Height modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Logo Height\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"a\",\"attribute\":\"class\",\"value\":\"logo\"},{\"tag\":\"img\",\"attribute\":\"\",\"value\":\"\"}],\"enabled\":true,\"parameter\":8,\"type\":\"height\",\"heightbase\":\"auto\",\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Logo Height\"]},{\"tooltip\":\"Width modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Logo Width\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"a\",\"attribute\":\"class\",\"value\":\"logo\"},{\"tag\":\"img\",\"attribute\":\"\",\"value\":\"\"}],\"enabled\":true,\"parameter\":7,\"type\":\"width\",\"widthbase\":\"450px\",\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Logo Width\"]},{\"tooltip\":\"Notify modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"Parameters\",\"path\":\"/neve-studio-dance-jacket.html\",\"find\":[{\"tag\":\"form\",\"attribute\":\"id\",\"value\":\"product_addtocart_form\"}],\"enabled\":true,\"targets\":[],\"type\":\"notify\",\"updated\":false,\"xpath\":\"\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\",\"Parameters\"]}],\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-magento.dnsalias.com\"]},{\"tooltip\":\"Company node\",\"type\":\"company\",\"children\":[{\"tooltip\":\"Division node\",\"type\":\"division\",\"children\":[{\"tooltip\":\"Site node\",\"type\":\"site\",\"children\":[{\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Change 7.62\",\"path\":\"//.*/\",\"extra\":\"7.62\",\"enabled\":true,\"cssselector\":\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"$7.00\"]},\"nodePath\":[\"Top\",\"legends-owo-secure.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Change 7.62\"]}],\"nodePath\":[\"Top\",\"legends-owo-secure.dnsalias.com\",\"example.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-owo-secure.dnsalias.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-owo-secure.dnsalias.com\"]},{\"tooltip\":\"Company node\",\"type\":\"company\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Division node\",\"type\":\"division\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Site node\",\"type\":\"site\",\"children\":[{\"name\":\"OWO Buy Tickets Change Order\",\"tooltip\":\"Order modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Change Order\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"\",\"enabled\":true,\"cssselector\":\"#ticket-package > div > div\",\"xpath\":\"\",\"find\":[],\"type\":\"order\",\"parameter\":3,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":true,\"orders\":[\"0,3,2,1,4,5,6\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Change Order\"]},{\"name\":\"OWO Buy Tickets Hero\",\"tooltip\":\"Style modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Hero\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"background\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header\",\"find\":[],\"type\":\"style\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"styles\":[\"#014f90 url(https://res.cloudinary.com/legends-production/image/upload/v1547115695/owo-prod/assets/ticket-package-combination.jpg) no-repeat\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Hero\"]},{\"name\":\"OWO Buy Tickets Hero Size\",\"tooltip\":\"Style modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Hero Size\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"background-size\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header\",\"find\":[],\"type\":\"style\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"styles\":[\"cover\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Hero Size\"]},{\"name\":\"OWO Buy Tickets Text\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Text\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"Buy\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section[1]/div/div/div/div/article[1]/header/div[3]/div/div/a\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"Visit Options\",\"Ticket Options\",\"Buy Options\",\"Purchase Now\",\"Reserve Now\",\"Buy Now\",\"Purchase Tickets\",\"Reserve Tickets\",\"Buy Tickets\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Text\"]},{\"name\":\"OWO Change Experience\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Change Experience\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"NYC Inspired Cuisine\",\"enabled\":true,\"cssselector\":\"h3.s-ticket-package-description__name\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"NYC Cusine for Oscar and Mike\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Change Experience\"]},{\"name\":\"OWO Fix Href\",\"tooltip\":\"Attribute modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Fix Href\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"href/useproxyhost\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"//*[@id=\\\"ticket-package\\\"]/div/div/article[3]/header/div[3]/div/div/a\",\"find\":[],\"type\":\"attribute\",\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Fix Href\"]},{\"name\":\"OWO Fix Iframe\",\"tooltip\":\"Modify modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Fix Iframe\",\"path\":\"//.*/\",\"extra\":\"fixiframesrc\",\"enabled\":true,\"cssselector\":\"#override\",\"xpath\":\"\",\"find\":[],\"type\":\"modify\",\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Fix Iframe\"]},{\"name\":\"OWO Free Cheesecake\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Free Cheesecake\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"\",\"enabled\":true,\"cssselector\":\"#id-116 + span\",\"xpath\":\"\",\"find\":[],\"type\":\"text\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"Free Cheesecake\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Free Cheesecake\"]},{\"name\":\"OWO Home Bottom Parameters\",\"tooltip\":\"Notify modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Parameters\",\"path\":\"/\",\"enabled\":true,\"targets\":[],\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"notify\",\"value\":null,\"values\":[],\"valueSuffix\":\"\",\"valuesCount\":0,\"updated\":false,\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Parameters\"]},{\"name\":\"OWO Home Bottom Style Color\",\"tooltip\":\"Style modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Style Color\",\"path\":\"/\",\"extra\":\"background-image\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"style\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"styles\":[\"linear-gradient(to left,#ffff00,#0000ff)\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Style Color\"]},{\"name\":\"OWO Home Bottom Style Width\",\"tooltip\":\"Style modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Style Width\",\"path\":\"/\",\"extra\":\"width\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"style\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"styles\":[\"250px\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Style Width\"]},{\"name\":\"OWO Home Bottom Text\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Text\",\"path\":\"/\",\"extra\":\"Buy Tickets\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"Visit Options\",\"Ticket Options\",\"Buy Options\",\"Purchase Now\",\"Reserve Now\",\"Buy Now\",\"Puchase Tickets\",\"Reserve Tickets\",\"Buy Tickets\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Text\"]},{\"name\":\"OWO Home Top Line Experience\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Top Line Experience\",\"path\":\"/\",\"find\":[],\"enabled\":true,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Buy From Chris\",\"Buy From Mr. Tanner\",\"Buy From Julian\",\"Buy Now!!!\",\"Top OWO Experience Now\"],\"updated\":false,\"xpath\":\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Home Top Line Experience\"]},{\"name\":\"OWO Home Top Line Tickets\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Top Line Tickets\",\"path\":\"/\",\"extra\":\"Buy Tickets\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"Visit Options\",\"Ticket Options\",\"Buy Options\",\"Purchase Now\",\"Reserve Now\",\"Buy Now\",\"Puchase Tickets\",\"Reserve Tickets\",\"Buy Tickets\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Home Top Line Tickets\"]},{\"name\":\"OWO Remove Mastercard\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Remove Mastercard\",\"path\":\"//.*/\",\"extra\":\"Mastercard\",\"enabled\":true,\"cssselector\":\"h3.s-ticket-package-description__name\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"A Guided Experience for Almost All\"]},\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Remove Mastercard\"]}],\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-owo.dnsalias.com\"]},{\"name\":\"legends-test-secure.dnsalias.com\",\"tooltip\":\"Company node\",\"type\":\"company\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Division node\",\"type\":\"division\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Site node\",\"type\":\"site\",\"children\":[{\"name\":\"OWO Change 7.62\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Change 7.62\",\"path\":\"//.*/\",\"extra\":\"7.62\",\"enabled\":true,\"cssselector\":\"#upsell-wizard > div > div > div:nth-child(4) > div > button > dynamic-locale:nth-child(3) > span:nth-child(2) > span\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"$7.00\"]},\"nodePath\":[\"Top\",\"legends-test-secure.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Change 7.62\"]}],\"nodePath\":[\"Top\",\"legends-test-secure.dnsalias.com\",\"example.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-test-secure.dnsalias.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-test-secure.dnsalias.com\"]},{\"name\":\"legends-test.dnsalias.com\",\"tooltip\":\"Company node\",\"type\":\"company\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Division node\",\"type\":\"division\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Site node\",\"type\":\"site\",\"children\":[{\"name\":\"OWO Bottom Parameters\",\"tooltip\":\"Notify modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Bottom Parameters\",\"path\":\"/\",\"enabled\":false,\"targets\":[],\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"notify\",\"value\":null,\"values\":[],\"valueSuffix\":\"\",\"valuesCount\":0,\"updated\":false,\"extra\":\"Extra Bott\"},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Bottom Parameters\"]},{\"name\":\"OWO Bottom Text\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Bottom Text\",\"path\":\"/\",\"find\":[],\"enabled\":false,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Buy Now!!!\",\"Buy Tickets Cheapest\"],\"updated\":false,\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Bottom Text\"]},{\"name\":\"OWO Change Experience\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Change Experience\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"NYC Inspired Cuisine\",\"enabled\":false,\"cssselector\":\"h3.s-ticket-package-description__name\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"NYC Cusine for Oscar and Mike\"]},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Change Experience\"]},{\"name\":\"OWO Change Order\",\"tooltip\":\"Order modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Change Order\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"\",\"enabled\":false,\"cssselector\":\"#ticket-package > div > div\",\"xpath\":\"\",\"find\":[],\"type\":\"order\",\"parameter\":3,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"orders\":[\"0,3,2,1,4,5,6\"]},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Change Order\"]},{\"name\":\"OWO Fix Href\",\"tooltip\":\"Attribute modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Fix Href\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"href/useproxyhost\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"//*[@id=\\\"ticket-package\\\"]/div/div/article[1]/header/div[3]/div/div/a\",\"find\":[],\"type\":\"attribute\",\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Fix Href\"]},{\"name\":\"OWO Fix Iframe\",\"tooltip\":\"Modify modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Fix Iframe\",\"path\":\"//.*/\",\"extra\":\"fixiframesrc\",\"enabled\":false,\"cssselector\":\"#override\",\"xpath\":\"\",\"find\":[],\"type\":\"modify\",\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Fix Iframe\"]},{\"name\":\"OWO Free Cheesecake\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Free Cheesecake\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"\",\"enabled\":false,\"cssselector\":\"#id-116 + span\",\"xpath\":\"\",\"find\":[],\"type\":\"text\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"Free Cheesecake\"]},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Free Cheesecake\"]},{\"name\":\"OWO Remove Mastercard\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Remove Mastercard\",\"path\":\"//.*/\",\"extra\":\"Mastercard\",\"enabled\":false,\"cssselector\":\"h3.s-ticket-package-description__name\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"A Guided Experience for Almost All\"]},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Remove Mastercard\"]},{\"name\":\"OWO Top Line Experience\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Top Line Experience\",\"path\":\"/\",\"find\":[],\"enabled\":false,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Buy From Chris\",\"Buy From Mr. Tanner\",\"Buy From Julian\",\"Buy Now!!!\",\"Top OWO Experience Now\"],\"updated\":false,\"xpath\":\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Top Line Experience\"]},{\"name\":\"OWO Top Line Tickets\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Top Line Tickets\",\"path\":\"/\",\"find\":[],\"enabled\":false,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Buy Now!!!\",\"Top OWO Tickets\"],\"updated\":false,\"xpath\":\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\",\"OWO Top Line Tickets\"]}],\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"legends-test.dnsalias.com\"]},{\"name\":\"oneworldobservatory.com\",\"tooltip\":\"Company node\",\"type\":\"company\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Division node\",\"type\":\"division\",\"children\":[{\"name\":\"example.com\",\"tooltip\":\"Site node\",\"type\":\"site\",\"children\":[{\"name\":\"OWO Buy Tickets 35 Text\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets 35 Text\",\"extra\":\"\",\"enabled\":true,\"type\":\"text\",\"path\":\"/en-US/buy-tickets\",\"cssselector\":\"\",\"xpath\":\"//*[@id=\\\"ticket-package\\\"]/div/div/article[3]/header/div[3]/div/div/a\",\"find\":[],\"parameter\":2,\"updated\":false,\"newtexts\":[\"Buy Tickets\",\"Reserve Tickets\",\"Purchase Tickets\",\"Buy Now\",\"Reserve Now\",\"Purchase Now\",\"Buy Options\",\"Ticket Options\",\"Visit Options\"]},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets 35 Text\"]},{\"name\":\"OWO Buy Tickets 55 Fix Href\",\"tooltip\":\"Attribute modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets 55 Fix Href\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"href/useproxyhost\",\"enabled\":false,\"cssselector\":\"\",\"xpath\":\"//*[@id=\\\"ticket-package\\\"]/div/div/article[1]/header/div[3]/div/div/a\",\"find\":[],\"type\":\"attribute\",\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets 55 Fix Href\"]},{\"name\":\"OWO Buy Tickets Change Experience\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Change Experience\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"NYC Inspired Cuisine\",\"enabled\":false,\"cssselector\":\"h3.s-ticket-package-description__name\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"NYC Cusine for Oscar and Mike\"]},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Change Experience\"]},{\"name\":\"OWO Buy Tickets Change Order\",\"tooltip\":\"Order modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Change Order\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"\",\"enabled\":true,\"cssselector\":\"#ticket-package > div > div\",\"xpath\":\"\",\"find\":[],\"type\":\"order\",\"parameter\":3,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"orders\":[\"0,3,2,1,4,5,6\"]},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Change Order\"]},{\"name\":\"OWO Buy Tickets Fix Iframe\",\"tooltip\":\"Modify modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Fix Iframe\",\"path\":\"//.*/\",\"extra\":\"fixiframesrc\",\"enabled\":false,\"cssselector\":\"#override\",\"xpath\":\"\",\"find\":[],\"type\":\"modify\",\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Fix Iframe\"]},{\"name\":\"OWO Buy Tickets Free Cheesecake\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Free Cheesecake\",\"path\":\"/en-US/buy-tickets\",\"extra\":\"\",\"enabled\":false,\"cssselector\":\"#id-116 + span\",\"xpath\":\"\",\"find\":[],\"type\":\"text\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"Free Cheesecake\"]},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Free Cheesecake\"]},{\"name\":\"OWO Buy Tickets Remove Mastercard\",\"tooltip\":\"Checked type modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Buy Tickets Remove Mastercard\",\"path\":\"//.*/\",\"extra\":\"Mastercard\",\"enabled\":true,\"cssselector\":\"h3.s-ticket-package-description__name\",\"xpath\":\"\",\"find\":[],\"type\":\"textchecked\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"newtexts\":[\"A Guided Experience\"]},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Buy Tickets Remove Mastercard\"]},{\"name\":\"OWO Home Bottom Parameters\",\"tooltip\":\"Notify modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Parameters\",\"path\":\"/\",\"enabled\":true,\"targets\":[],\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"notify\",\"value\":null,\"values\":[],\"valueSuffix\":\"\",\"valuesCount\":0,\"updated\":false,\"extra\":\"\"},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Parameters\"]},{\"name\":\"OWO Home Bottom Style Color\",\"tooltip\":\"Style modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Style Color\",\"path\":\"/\",\"extra\":\"background-image\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"style\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"styles\":[\"linear-gradient(to left,#f05b83,#003f6c\"]},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Style Color\"]},{\"name\":\"OWO Home Bottom Style Width\",\"tooltip\":\"Style modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Style Width\",\"path\":\"/\",\"extra\":\"width\",\"enabled\":true,\"cssselector\":\"\",\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"find\":[],\"type\":\"style\",\"parameter\":2,\"value\":null,\"valueSuffix\":\"\",\"values\":[],\"valuesCount\":0,\"updated\":false,\"styles\":[\"150px\"]},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Style Width\"]},{\"name\":\"OWO Home Bottom Text\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Bottom Text\",\"path\":\"/\",\"find\":[],\"enabled\":true,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Buy Tickets\",\"Reserve Tickets\",\"Purchase Tickets\",\"Buy Now\",\"Reserve Now\",\"Purchase Now\",\"Buy Options\",\"Ticket Options\",\"Visit Options\"],\"updated\":false,\"xpath\":\"/html/body/div[1]/main/section/div/div/div/section/div/div/a\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Home Bottom Text\"]},{\"name\":\"OWO Home Top Line Experience\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Top Line Experience\",\"path\":\"/\",\"find\":[],\"enabled\":true,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Experience\",\"Experience\"],\"updated\":false,\"xpath\":\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[2]/div/a\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Home Top Line Experience\"]},{\"name\":\"OWO Home Top Line Tickets\",\"tooltip\":\"Text modification\",\"type\":\"mod\",\"children\":[],\"details\":{\"name\":\"OWO Home Top Line Tickets\",\"path\":\"/\",\"find\":[],\"enabled\":true,\"parameter\":2,\"type\":\"text\",\"newtexts\":[\"Buy Tickets\",\"Reserve Tickets\",\"Purchase Tickets\",\"Buy Now\",\"Reserve Now\",\"Purchase Now\",\"Buy Options\",\"Ticket Options\",\"Visit Options\"],\"updated\":false,\"xpath\":\"/html/body/div/div/div/div[2]/div/div/div/nav/ul/li[1]/div/a\",\"cssselector\":\"\",\"extra\":\"\"},\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\",\"OWO Home Top Line Tickets\"]}],\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"oneworldobservatory.com\",\"example.com\"]}],\"nodePath\":[\"Top\",\"oneworldobservatory.com\"]}],\"nodePath\":[\"Top\"]}]}]}";
  /* Return a single string value by combining the parts. Note that the final string
     contains exactly one character (the copyright symbol) that is not also an ASCII
     character. As a consequence the string built below is 120960 characters in length.
     The original file (which appears to be in UTF-8 format contains 120961 bytes.

     The string was broken down into parts because the system had trouble handling the
     original string in one piece. Some limit around 64K was reached. */
  protected static String magentoNeveJacketStr() {
  	StringBuilder  out = new StringBuilder();
  	out.append(magentoNeveJacketHtmlStrPart1);
  	out.append(magentoNeveJacketHtmlStrPart2);
  	out.append(magentoNeveJacketHtmlStrPart3);
		out.append(magentoNeveJacketHtmlStrPart4);
		out.append(magentoNeveJacketHtmlStrPart5);
		out.append(magentoNeveJacketHtmlStrPart6);
		out.append(magentoNeveJacketHtmlStrPart7);
		out.append(magentoNeveJacketHtmlStrPart8);
		out.append(magentoNeveJacketHtmlStrPart9);
		out.append(magentoNeveJacketHtmlStrPart10);
		out.append(magentoNeveJacketHtmlStrPart11);
		out.append(magentoNeveJacketHtmlStrPart12);
		out.append(magentoNeveJacketHtmlStrPart13);
		out.append(magentoNeveJacketHtmlStrPart14);
  	return out.toString();
  }
}