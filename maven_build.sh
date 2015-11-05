# Maven build script

# Build cy3sbml latest devlop from source
CY3SBML_VERSION="0.1.7"
cd $CY3SBML
mvn install -DskipTests

# copy in local maven repo
mvn install:install-file -DgroupId=cy3sbml-temp -DartifactId=cy3sbml -Dversion=$CY3SBML_VERSION -Dfile=$CY3SBML/target/cy3sbml-$CY3SBML_VERSION.jar -Dpackaging=jar -DgeneratePom=true

cd $CY3FLUXVIZ
mvn install 

