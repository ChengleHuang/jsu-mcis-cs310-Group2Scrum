package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest 
{
	private ArgumentParser parser;
	
	@Before
	public void StartUp()
	{
		parser = new ArgumentParser();
	}
	
	@Test
	public void testSingleArgumentAdder()
	{
		String[] myString = new String[1];
		myString[0] = "4";
		parser.addArg("length");
		parser.adder(myString);
		assertEquals("4", parser.getArgumentValue("length"));
	}
	
	@Test
	public void testDoubleArgumentAdder()
	{
		String[] myString = new String[2];
		myString[0] = "4";
		myString[1] = "7";
		parser.addArg("length");
		parser.addArg("width");
		parser.adder(myString);
		assertEquals("4", parser.getArgumentValue("length"));
		assertEquals("7", parser.getArgumentValue("width"));
	}
	
	@Test
	public void testMultipleArgumentAdder()
	{
		String[] myString = new String[3];
		myString[0] = "4";
		myString[1] = "7";
		myString[2] = "9";
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		parser.adder(myString);
		assertEquals("4", parser.getArgumentValue("length"));
		assertEquals("7", parser.getArgumentValue("width"));
		assertEquals("9", parser.getArgumentValue("height"));
	}
	
	@Test
	public void testCompleteParsing()
	{
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		assertEquals("Parsing Completed", parser.parse("VolumeCalculator 7 5 2"));
	}
	
	@Test
	public void testTooFewArguments()
	{
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following arguments are required: height", 
					parser.parse("VolumeCalculator 7 5"));
	}
	
	@Test
	public void testTooManyArguments()
	{
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: unrecognized arguments: 43", 
					parser.parse("VolumeCalculator 7 5 2 43"));
	}
	
	@Test
	public void testProgramHelp()
	{
		parser.addArgumentHelp("-h", "usage: java VolumeCalculator length width height\n");
		assertEquals("usage: java VolumeCalculator length width height\n" + "\n" +"Calculate the volume of a box.\n "+"\n"+
						"positional arguments: length the length of the box\n"+"width the width of the box\n"+"height the height of the box\n", 
							parser.getHelpArgumentValue("-h"));
	}
	
	@Test
	public void testAddHelpWithArgument() 
	{
		parser.addArgumentHelp("length", "Please enter the length as a whole number");
		assertEquals("   Please enter the length as a whole number   ", parser.getHelpArgumentValue("length"));
	}
	
	@Test
	public void testParseAString()
	{	
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		parser.parse("VolumeCalculator 7 6 2");
		assertEquals("7", parser.getArgumentValue("length"));
		assertEquals("6", parser.getArgumentValue("width"));
		assertEquals("2", parser.getArgumentValue("height"));
	}
	
}