/**
 * Hiba IpServeur :192.168.1.13
 */
/**
 * Farouk IpServeur :192.168.2.150
 */
angular.module('config', [ "ngResource" ])
    .constant('baseUrl', "http://localhost:8080/mt-gpro-commun-rest")
	.constant('baseUrlGc', "http://localhost:8080/ma-gpro-gc-rest")
	.constant('baseUrlGpao', "http://localhost:8080/ma-gpro-gpao-rest")
	.constant('baseUrlGs', "http://localhost:8080/ma-gpro-gs-rest")
	.constant('baseUrlGgpro', "http://localhost:8080/ma-gpro/#")

	.constant('ARTICLE_REF_EXIST_ERROR', 'C01')
	.constant('ARTICLE_CODE_FOUR_EXIST_ERROR', 'C02')
    .constant('PRODUIT_REF_EXIST_ERROR', 'C03')
    
    .constant('OF_NUMERO_EXIST_ERROR', 'GPAO01')
    .constant('FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR', 'GPAO02')

    

