La partie d est faite :
(voir fichier RushHour2) Le joueur peut, à travers un menu console, décider s'il veut choisir lui même le niveau et/ou le numéro de configuration.
Ce qu'il n'a pas choisit est décidé aléatoirement.

ParkingFactory
Celui-ci prend en paramètre le niveau et la configuration choisit. Grâce à celà il importe les bons fichiers et la bonne ligne. Il fait un mixte entre les données des
voiture (fichier vehicles.dat) et la ligne du niveau dans (1-beginner.cfg (ou autre)). 
En ayant par exemple X2red et XH02 il peut faire un mixte et utiliser la méthode Parking.addVéhicule(X, 2, 1, 0, 2, "red")
Le code n'est pas testé, en réalité il bug en essayant d'importer le fichier 1-beginner.cfg, certainement un problème au niveau du chemin relatif dans la méthode chargerFicher


Player
Celui-ci est juste initialisé, il faudra l'adapter.