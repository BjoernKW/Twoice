package twoice

class FileConversionService {

    static transactional = false

	def servletContext

    def convert(def filename) {
		println "Processing file: ${filename}"
		
		def filenameGroups = filename =~ /(.+)(\.[A-Za-z0-9]{2,})$/
		def pcmFilename = "${filenameGroups[0][1]}.pcm.wav"
		def convertedFilename = "${filenameGroups[0][1]}.mp3"
		
		println "###############"
		def shellCommand = ['sox', "${filename}", '-s', '-b', '16', "${pcmFilename}"] 
		println shellCommand
		println "---------------"

		def process = shellCommand.execute()
		def out = new StringBuffer()
		def err = new StringBuffer()
		process.consumeProcessOutput(out, err)
		try {
			process.waitFor()
			println "${out}"
			println "---------------"
			println "${err}"
		} catch (InterruptedException) {
		}
		
		shellCommand = ['lame', '-h', "${pcmFilename}", "${convertedFilename}"]
		println shellCommand
		println "---------------"

		process = shellCommand.execute()
		out = new StringBuffer()
		err = new StringBuffer()
		process.consumeProcessOutput(out, err)
		try {
			process.waitFor()
			println "${out}"
			println "---------------"
			println "${err}"
		} catch (InterruptedException) {
		}
		
		return new File(convertedFilename).name
    }
}
