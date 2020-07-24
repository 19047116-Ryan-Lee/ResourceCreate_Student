import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;

	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;

	public ResourceCentreTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList = new ArrayList<Camcorder>();
		chromebookList = new ArrayList<Chromebook>();
	}

	@Test
	public void addCamcorderTest() {
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);

		// Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());

		// The item just added is as same as the first item of the list
		assertSame("Test that Camcorder is added same as 1st item of the list?", cc1, camcorderList.get(0));

		// Add another item. test The size of the list is 2?
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, camcorderList.size());
	}

	@Test
	public void addChromebookTest() {
		// fail("Not yet implemented");
		// write your code here
		// Ijas
		// Checks if there is an available arraylist for the chromebook
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);

		// If the arraylist is empty, this will test if the size has increase after
		// adding in a new element
		ResourceCentre.addChromebook(chromebookList, cb1);
		assertEquals("Test if that Chromebook arraylist size is 1?", 1, chromebookList.size());

		// the first item of the list should be the chromebook added in the previous
		// codes
		assertSame("Test that the Chromebook is added same as 1st item of the list?", cb1, chromebookList.get(0));

		// Adds another chormebook and test if the size has increased by 2
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test Chromebook arraylist size is 2?", 2, chromebookList.size());
	}

	@Test
	public void retrieveAllCamcorderTest() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);

		// test if the list of camcorders retrieved from the SourceCentre is empty
		String allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		String testOutput = "";
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());

		// test if the expected output string same as the list of camcorders retrieved
		// from the SourceCentre
		allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0012", "Sony DSC-RX100M7", "Yes", "", 20);

		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

	}

	@Test
	public void retrieveAllChromebookTest() {
		// fail("Not yet implemented");
		// Ijas
		// Test if the chromebook arraylist is available and not null
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);
		// test if the retrieved chromebook is empty
		String allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		String testOutput = "";
		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);

		// Since the list is empty, check if the arraylist has a bigger size now after
		// adding 2 new chromebooks
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test if that Chromebook arraylist size is 2?", 2, chromebookList.size());
		
		// test if the chromebookList retrieved matches the chromebooks added into the
		// list previously
		allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		testOutput = "";
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0011", "My Google Chromebook 1st", "Yes", "",
                "Mac OS");
        testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0012", "SAMSUNG Chromebook 4+", "Yes", "",
                "Win 10");
        assertEquals("Check that both strings are equal", testOutput, allChromebook);
	}

	@Test
	public void doLoanCamcorderTest() { // Ryan Lee
		// fail("Not yet implemented");
		// write your code here
		// Test if the item list is not null, so that items can be loaned
		assertNotNull("Test if there is a valid Camcorder ArrayList to loan from", camcorderList);

		// Test that given an item list of 2 items, when 1 item(CC0012) is loaned, that
		// item in the item list is not available.

		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		String assetTag = "CC0012";
		String due = "21/1/21";
		boolean check = ResourceCentre.doLoanCamcorder(camcorderList, assetTag, due);
		//Check if assetTag CC0012 was able to be loaned out
		assertTrue(check);
		// This is to check if the item CC0012 is not available anymore.
		for (Camcorder item : camcorderList) {
			if (item.getAssetTag() == "CC0012") { 
				assertFalse(item.getIsAvailable());
			}
		}
		// test
		// Test that if an input has an incorrect asset tag, the system is able to
		// detect it's false
		String incorrectTag = "DD0012";
		due = "";
		assertFalse("Check if system returns false when tag is incorrect",
				ResourceCentre.doLoanCamcorder(camcorderList, incorrectTag, due));

	}

	@Test
	public void doLoanChromebookTest() {
		// fail("Not yet implemented");
		// write your code here
	}

	@Test
	public void doReturnCamcorderTest() {
		// fail("Not yet implemented");
		// write your code here Sebastian
		assertNotNull("Test for valid camcorder list", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		// error because the camcorder was not loaned out
		Boolean isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertFalse("Test if available camcorder CC011 returns false", isReturned);
		// normal test condition becausse the camcorder was loaned out and returned
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0012");
		assertTrue("Test if loaned out camcorder CC0012 returns true", isReturned);
		// this is an error condition for a non existing camcorder
		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0013");
		assertFalse("Test if non-existing camcorder CC0013 returns false", isReturned);
	}

	@Test
	public void doReturnChromebookTest() {
		// fail("Not yet implemented");
		// write your code here
	}

	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
