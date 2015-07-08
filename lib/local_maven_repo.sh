# Add latest cy3sbml target to local maven repository 
# change in the lib directory
cd $CY3SBML/target
# add to local repository
mvn install:install-file -DgroupId=cy3sbml-temp -DartifactId=cy3sbml -Dversion=0.1.6 -Dfile=cy3sbml-0.1.6.jar -Dpackaging=jar -DgeneratePom=true

