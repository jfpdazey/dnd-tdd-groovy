package codemash.evercraft;
import spock.lang.*

class HelloWorldTest extends Specification {
	
	def sayer, sayerTwo
	
	void setup() {
		sayerTwo = new HelloWorldSayer()
	}
	
	def testHello() {
		when:
			sayer = new HelloWorldSayer()
		then:
			sayer.say() == "Hello World"
	}
	
	def testHelloWithGivenExpect() {
		given:
			sayer = new HelloWorldSayer()
		expect:
			sayer.say() == "Hello World"
	}
	
	def testSimplerHelloWorld() {
		expect:
			sayerTwo.say() == "Hello World"
	}
}
