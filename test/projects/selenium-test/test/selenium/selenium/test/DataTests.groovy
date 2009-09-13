package selenium.test

import com.energizedwork.grails.plugins.seleniumrc.GrailsSeleneseTestCase

class DataTests extends GrailsSeleneseTestCase {

	void testListPage() {
		Song.withTransaction {
			Song.build(title: "Heads Will Roll", artist: "Yeah Yeah Yeahs", album: "It's Blitz!", durationSeconds: 221)
			Song.build(title: "Twilight Galaxy", artist: "Metric", album: "Fantasies", durationSeconds: 293)
			Song.build(title: "I'm Confused", artist: "Handsome Furs", album: "Face Control", durationSeconds: 215)
		}

		selenium.open "/selenium-test/song/list"

		assertEquals "Title", selenium.getText("//table/thead/tr/th[2]")
		assertEquals "Artist", selenium.getText("//table/thead/tr/th[3]")
		assertEquals "Album", selenium.getText("//table/thead/tr/th[4]")
		assertEquals "Duration Seconds", selenium.getText("//table/thead/tr/th[5]")

		selenium.click("link=Title")
		selenium.waitForPageToLoad("5000")

		assertEquals "Heads Will Roll", selenium.getText("//table/tbody/tr[1]/td[2]")
		assertEquals "I'm Confused", selenium.getText("//table/tbody/tr[2]/td[2]")
		assertEquals "Twilight Galaxy", selenium.getText("//table/tbody/tr[3]/td[2]")
	}

}