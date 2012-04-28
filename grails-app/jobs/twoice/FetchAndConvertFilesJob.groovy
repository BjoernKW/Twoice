package twoice

class FetchAndConvertFilesJob {
    def timeout = 5.minutes

	def mailFetcherService
	def fileConversionService
	def twoiceEntryHelperService

    def execute() {
        def filenames = mailFetcherService.fetchNewMessages()
		filenames.each { filename ->
			if (filename) {
				def convertedFilename = fileConversionService.convert(filename)
				twoiceEntryHelperService.assignEntry(new TwoiceEntry(filename:convertedFilename))
			}
		}
    }
}
