# If not stated otherwise in this file or this component's license file the
# following copyright and licenses apply:
#
# Copyright 2020 RDK Management
#
# Licensed under the Apache License, Version 2.0 (the License);
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an AS IS BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

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
    fd = open(file, 'r')
    file_content = fd.read()
    fd.close()

    # Use RE package to allow for replacement (also allowing for (multiline) REGEX)
    file_content = (re.sub("[a-z0-9]{40}", newVersion, file_content))

    # Write contents to file.
    # Using mode 'w' truncates the file.
    fd = open(file, 'w')
    fd.write(file_content)
    fd.close()	

def main(argv):
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
