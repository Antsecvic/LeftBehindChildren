package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Util.Statistics;


public class StatisticsTest {

	int [] type={1,0,2,3};
	int [] theme={1,0,2,3};
	int [] source={1,0,2,3};
	int [] showing={1,0,2,3};
	int [] reason={1,0,2,3};
	int [] mainBody={1,0,2,3};
	int [] helpType={1,0,2,3};
	int [] sexualAssaultGender={1,0,2,3};
	int [] violenceGender={1,0,2,3};
	int [] crimeGender={1,0,2,3};
	int [] positiveHealthGender={1,0,2,3};
	
	private static Statistics testS=new Statistics();
	
	@Test
	public void testGetType() {
		type=testS.getType();
		assertEquals(0,type[1]);
	}


	@Test
	public void testGetTheme() {
		theme=testS.getTheme();
		assertEquals(0,theme[1]);
	}

	@Test
	public void testGetSource() {
		source=testS.getSource();
		assertEquals(0,source[1]);
	}

	@Test
	public void testGetShowing() {
		showing=testS.getShowing();
		assertEquals(1,showing[1]);
	}

	@Test
	public void testGetReason() {
		reason=testS.getReason();
		assertEquals(1,reason[0]);
	}

	@Test
	public void testGetMainBody() {
		mainBody=testS.getMainBody();
		assertEquals(0,mainBody[1]);
	}

	@Test
	public void testGetHelpType() {
		helpType=testS.getHelpType();
		assertEquals(0,helpType[1]);
	}

	@Test
	public void testGetSexualAssaultGender() {
		sexualAssaultGender=testS.getSexualAssaultGender();
		assertEquals(0,sexualAssaultGender[1]);
	}

	@Test
	public void testGetViolenceGender() {
		violenceGender=testS.getViolenceGender();
		assertEquals(0,violenceGender[1]);
	}

	@Test
	public void testGetCrimeGender() {
		crimeGender=testS.getCrimeGender();
		assertEquals(0,crimeGender[1]);
	}

	@Test
	public void testGetPositiveHealthGender() {
		positiveHealthGender=testS.getPositiveHealthGender();
		assertEquals(0,positiveHealthGender[1]);
	}

}
