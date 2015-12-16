import sys, getopt, re, fileinput

def findRev(file):
	f = open(file, "r")
	data = f.read()
	m = re.search(r"[a-z0-9]{40}", data)
    	if m:
			#print "Found VERSION: " + m.group()
			return m.group()

def updateOeVersion(file, newVersion):
    # Read contents from file as a single string
    file_handle = open(file, 'r')
    file_string = file_handle.read()
    file_handle.close()

    # Use RE package to allow for replacement (also allowing for (multiline) REGEX)
    file_string = (re.sub("[a-z0-9]{40}", newVersion, file_string))

    # Write contents to file.
    # Using mode 'w' truncates the file.
    file_handle = open(file, 'w')
    file_handle.write(file_string)
    file_handle.close()	

def main(argv):
	print "-------------------"
	inputfile = ''
	outputfile = ''
	try:
		opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
	except getopt.GetoptError:
		print 'check_srcrevs.py -i <inputfile> -o <outputfile>'
		sys.exit(2)
	for opt, arg in opts:
		if opt == '-h':
			print 'check_srcrevs.py -i <inputfile> -o <outputfile>'
			sys.exit()
		elif opt in ("-i", "--ifile"):
			inputfile = arg
		elif opt in ("-o", "--ofile"):
			outputfile = arg
	#print 'Input file is "', inputfile
	#print 'Output file is "', outputfile

	buildrootVersion = findRev(inputfile)
	oeVersion = findRev(outputfile)

	if buildrootVersion != oeVersion:
		print "File: " + outputfile + " has an outdated version, syncing to buildroot version"
		print "Current version: " + oeVersion
		print "Version in buildroot: " + buildrootVersion
		updateOeVersion(outputfile,  buildrootVersion)
		print "-------------------"
	else:
		print "File: " + outputfile + " is up2date with version: " + oeVersion
		print "-------------------"

if __name__ == "__main__":
	main(sys.argv[1:])
