La partie d est faite :
(voir fichier RushHour2) Le joueur peut, � travers un menu console, d�cider s'il veut choisir lui m�me le niveau et/ou le num�ro de configuration.
Ce qu'il n'a pas choisit est d�cid� al�atoirement.

ParkingFactory
Celui-ci prend en param�tre le niveau et la configuration choisit. Gr�ce � cel� il importe les bons fichiers et la bonne ligne. Il fait un mixte entre les donn�es des
voiture (fichier vehicles.dat) et la ligne du niveau dans (1-beginner.cfg (ou autre)). 
En ayant par exemple X2red et XH02 il peut faire un mixte et utiliser la m�thode Parking.addV�hicule(X, 2, 1, 0, 2, "red")
Le code n'est pas test�, en r�alit� il bug en essayant d'importer le fichier 1-beginner.cfg, certainement un probl�me au niveau du chemin relatif dans la m�thode chargerFicher


Player
Celui-ci est juste initialis�, il faudra l'adapter.