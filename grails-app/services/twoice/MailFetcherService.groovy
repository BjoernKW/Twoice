package twoice

import javax.mail.*
import javax.mail.search.*
import javax.mail.internet.*

class MailFetcherService {

    static transactional = false

	def grailsApplication
	def servletContext

    def fetchNewMessages() {
		def filenames = []
		def messages
		
		Properties properties = new Properties()
		properties.setProperty('mail.store.protocol', 'imap')
		properties.setProperty('mail.imap.host', grailsApplication.config.grails.mail.incomingHost)
		properties.setProperty('mail.imap.port', grailsApplication.config.grails.mail.incomingPort)
		properties.setProperty('mail.imap.socketFactory.class', 'javax.net.ssl.SSLSocketFactory')
		properties.setProperty('mail.imap.socketFactory.fallback', 'false')
		properties.setProperty('mail.imap.socketFactory.port', grailsApplication.config.grails.mail.incomingPort)

		def session = Session.getDefaultInstance(properties, null)
		def store = session.getStore('imap')
		def inbox
		def folder
		
		try {
			store.connect(grailsApplication.config.grails.mail.incomingHost,
				grailsApplication.config.grails.mail.incomingUsername,
				grailsApplication.config.grails.mail.incomingPassword)
			folder = store.getDefaultFolder()
			inbox = folder.getFolder('INBOX')
			inbox.open(Folder.READ_WRITE)
			messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.DELETED), false))
			messages.each { message ->
				def recipients = message.getRecipients(Message.RecipientType.TO)
				if ("${recipients[0]}" == grailsApplication.config.mailToAddress) {
					println "eMail received: ${message.subject}"
				
					def content = message.getContent()
					if (content instanceof Multipart) {
						filenames.addAll(handleMultipart((Multipart)content))
					} else {
						handlePart(message)
					}
					message.setFlag(Flags.Flag.DELETED, true)
				}
			}
		} finally {
			if(inbox && inbox?.isOpen()) {
				inbox.close(true)
		  	}
		  	store.close()
		}
		
		return filenames
    }

	private List handleMultipart(Multipart multipart) throws MessagingException, IOException {
		def filenames = []
		
		int n = multipart.getCount()
	    for (int i = 0; i < n; i++) {
			filenames.add(handlePart(multipart.getBodyPart(i)))
		}
		
		return filenames
	}
	
	private String handlePart(Part part) throws MessagingException, IOException {
		String filename
		
	    String disposition = part.getDisposition()
	    String contentType = part.getContentType()
	
		if (disposition == null) {
		    if ((contentType.length() >= 10)
				&& (contentType.toLowerCase().substring(0, 10).equals('text/plain'))) {
		    } else {
		        // don't think this will happen
		    }
		} else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
		    filename = saveFile(part.getFileName(), part.getInputStream())
		} else if (disposition.equalsIgnoreCase(Part.INLINE)) {
		    filename = saveFile(part.getFileName(), part.getInputStream())
		} else {
		    // this should actually never happen
		}
		
		return filename
	}

	private String saveFile(String filename, InputStream input) throws IOException {
	    def folder = "${servletContext.getRealPath('/')}audioFiles"
		if (filename == null) {
	        filename = "${folder}/${System.currentTimeMillis()}"
	    } else {
			filename = "${folder}/${filename}"
		}
		
	    // don't overwrite existing file
	    File file = new File(filename)
	    for (int i = 0; file.exists(); i++) {
			def filenameGroups = filename =~ /(.+)\.([0-9]+?)(\.[A-Za-z0-9]{2,})$/
			filename = "${filenameGroups[0][1]}-${i}.${filenameGroups[0][2]}${filenameGroups[0][3]}"
	        file = new File(filename)
	    }
		file.createNewFile()
		
		println "Storing file: ${filename}"
		
	    FileOutputStream fos = new FileOutputStream(file)
	    BufferedOutputStream bos = new BufferedOutputStream(fos)

	    BufferedInputStream bis = new BufferedInputStream(input)
	    int aByte
	    while ((aByte = bis.read()) != -1) {
	        bos.write(aByte)
	    }
	
	    bos.flush()
	    bos.close()
	    bis.close()
	
		return filename
	}
}
