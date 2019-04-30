package constants;

public class htmlConstants {
	public static String htmlHead (String title)
	{
		String openHtml = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head><title>" + title + "</title>" + "<link rel=\"stylesheet\" href=\"styles/styles.css\" type=\"text/css\"/>\n"
						+ "</head>"
				+ "<body>";
		return openHtml;
	}
	
	public static String headers (String header)
	{
		String head = "<h1>" + header + "</h1>";
		return head;
	}
	
	public static String td (String value)
	{
		String tableCell = "<td>" + value + "</td>";
		return tableCell;
	}
	
	public static String th (String header)
	{
		String tableHeader = "<th>" + header + "</th>";
		return tableHeader;
	}
	
	public static String button (String action, String name)
	{
		String form = "<form action=\"" + action +  "\" method=\"POST\">" +
						"<input type=\"submit\" value=\"" + name + "\">" + "</form>";
		return form;
		
	}
	public static String closeHtml = "</body>\n" + "</html>";
	public static String tableStart = "<table>";
	public static String trStart = "<tr>";
	public static String trEnd = "</tr>";
	public static String tableEnd = "</table>";
	public static String actionDropDown = "<select>\n" + 
			"  <option value=\"buy\">Buy</option>\n" + 
			"  <option value=\"sell\">Sell</option>\n" +
			"</select>";
	public static String textInput = "<input type=\"text\" name=\"quantity\" value=\"#\">";
	public static String logOff = "<form action=\"LogOutControllerServlet\">\n" + 
			"<input type=\"submit\" value=\"Log out\" />\n" + 
			"</form>";
}

