# Add latest cy3sbml target to local maven repository
# copy the rest of dependencies from cy3sbml

# lib directory
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# change in the lib directory
cd $CY3SBML/target
# add to local repository
mvn install:install-file -DgroupId=cy3sbml-dep -DartifactId=cy3sbml -Dversion=0.1.8 -Dfile=cy3sbml-0.1.8.jar -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=$DIR -DcreateChecksum=true


