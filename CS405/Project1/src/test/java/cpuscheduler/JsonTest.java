package cpuscheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	//Json strings to be ingested
	private String singlePCB = "{\"processID\":\"p1\",\"cpuBursts\":[1,2,5],\"ioBursts\":[1,2,5]}";
	private String listPCBs = "[{\"processID\":\"p1\",\"cpuBursts\":[1,2,5],\"ioBursts\":[1,2,5]},{\"processID\":\"p2\",\"cpuBursts\":[5,2,1],\"ioBursts\":[5,2,1]},{\"processID\":\"p3\",\"cpuBursts\":[3,4,7],\"ioBursts\":[3,4,7]},{\"processID\":\"p4\",\"cpuBursts\":[7,4,3],\"ioBursts\":[7,4,3]}]";
	private String zeroPCB = "{\"processID\":\"p1\",\"cpuBursts\":[1,0,5],\"ioBursts\":[1,2,5]}";
	private String negPCB = "{\"processID\":\"p1\",\"cpuBursts\":[1,2,5],\"ioBursts\":[1,-2,5]}";
	
	private static ObjectMapper m = new ObjectMapper();

	//Add case when there is only 1 PCB
	@Test
	public void ingestSinglePCBTest() {
		try {
			ProcessControlBlock pcb = m.readValue(singlePCB, ProcessControlBlock.class);
			
			//Ensure CPU and IO burst times were correctly injested
			assertEquals("pcb burst at index 0 should be 1", 1, pcb.getCpuBursts().get(0).intValue());
			assertEquals("io burst at index 0 should be 1", 1, pcb.getIOBursts().get(0).intValue());
			
			//Ensure remaining CPU and IO burst times were correctly set
			assertEquals("current cpu process burst time should be 1", 1, pcb.getCountCPU());
			assertEquals("current io process burst time should be 1", 1, pcb.getCountIO());
			
		} catch (JsonParseException e) {
			fail(e.toString());
		} catch (JsonMappingException e) {
			fail(e.toString());
		} catch (IOException e) {
			fail(e.toString());
		}
	}
	
	//Add case when importing a list of PCBs
	@Test
	public void ingestMultiplePCBTest() {
		try {
			List<ProcessControlBlock> pcbs = m.readValue(listPCBs, new TypeReference<List<ProcessControlBlock>>() {});
			pcbs.forEach(pcb -> {
				assertNotNull(pcb.getProcessID());
			}); 
		} catch (IOException e) {
			fail(e.toString());
		}
	}
	
	//TODO Add case when a CPU or IO burst is zero or negative
	@Test(expected = com.fasterxml.jackson.databind.exc.InvalidDefinitionException.class)
	public void ingestZeroPCBTest() throws Exception{
		@SuppressWarnings("unused")
		ProcessControlBlock pcb = m.readValue(zeroPCB, ProcessControlBlock.class);
	}
	
	@Test(expected = com.fasterxml.jackson.databind.exc.InvalidDefinitionException.class)
	public void ingestNegPCBTest() throws Exception {
		@SuppressWarnings("unused")
		ProcessControlBlock pcb = m.readValue(negPCB, ProcessControlBlock.class);
	}

}
