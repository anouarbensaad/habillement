'use strict'

angular
				.module('gpro.planning',
					[ 'ui.bootstrap', 'ngAnimate', 'ngSanitize', 'mwl.calendar', 'ui.calendar', 'ngResource', 'ngQuickDate'])
				.controller(
					'planningController',
					[
					'$scope',
					'$filter',
					'$http',
					'$log',
					'$compile',
					'$rootScope',
					'moment',
					'$window',
					'calendarConfig',
					'uiCalendarConfig',
					'baseUrlGpao',
					'baseUrl',
					'$parse',
					'$location',

					function($scope, $filter, $http, $log, $compile,
						$rootScope, moment, $window, CalendarConfig, uiCalendarConfig,
						baseUrlGpao,baseUrl,$parse,$location) {
											//var data;
											$scope.mode = "add";
											$scope.commandeIsSaved= false;
											$scope.showCheckbox = true;
											$scope.listChaineAtelier= [];
											$scope.listeProduit = [];
											$scope.ordreFabricationCourant  = {};
											$scope.ListSousFamilleProduitCache = [];
											$scope.displayMode = "list";
											$scope.numeroExistError = false;
											$scope.alertMsg = '';

					/**************************Liste des chaines Atelier****************************/
							
							$scope.listeChaineAtelier = function() {
								$http
								.get( 
									baseUrl 
									+"/sitePartieIntersse/all")
								.success(
									function(resultat) {
										$scope.listeChaineAtelier = resultat;

									});
							}
							$scope.listeChaineAtelier();

					/********************************Notif*****************************************/

							$scope.hiddenNotif = true;

							$scope.showNotif = function() {
								$scope.hiddenNotif = false;
							}

							$scope.closeNotif = function() {
								$scope.hiddenNotif = true;
							}	


					/*********************************************************************************/

							$scope.checkEditableEvent = function(dateFinPrevue){
								
								var today = new Date();
								var todayYear = today.getYear();
								var todayMonth = today.getMonth();
								var todayDay = today.getDate();
								
								var tomorrowDate = new Date(dateFinPrevue.getTime() + 24 * 60 * 60 * 1000);
								var year = tomorrowDate.getYear();
								var month = tomorrowDate.getMonth()+1;
								var day = tomorrowDate.getDate();
								
								//console.log("dateFinPrevue : day" + dateFinPrevue.getDate());
								//console.log("tomorrowDate : day" + tomorrowDate.getDate());
								
											
											if(todayYear == year){
												
												if(todayMonth == month){
													
													if(todayDay >= day){
														return false;
													}else{
														return true;
													}
													
												}else if (todayMonth < month){
													return true;
												}else{
													return false;
												}
												
											}else if (todayYear < year){
												return true;
											}else{
												return false;
											}
										}

					/********************************************Date picker *************************************************/
							$scope.color = { 
								"primary": "#e3bc08",
								"secondary": "#fdf1ba",

							}
							$scope.textColor= {
								primary: 'yellow'
							}
							$scope.inlineOptions = {
								customClass: getDayClass,
								minDate: new Date(),
								showWeeks: true
							};

							$scope.dateOptions = {
								dateDisabled: disabled,
								formatYear: 'yy',
								maxDate: new Date(2020, 5, 22),
								minDate: new Date(),
								startingDay: 1
							};
							  // Disable weekend selection
							  function disabled(data) {
							  	var date = data.date,
							  	mode = data.mode;
							  	return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
							  }
							  $scope.open2 = function() {
							  	$scope.popup2.opened = true;
							  };
							  $scope.popup2 = {
							  	opened: false
							  };

							  function getDayClass(data) {
							  	var date = data.date,
							  	mode = data.mode;
							  	if (mode === 'day') {
							  		var dayToCheck = new Date(date).setHours(0,0,0,0);

							  		for (var i = 0; i < $scope.events.length; i++) {
							  			var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

							  			if (dayToCheck === currentDay) {
							  				return $scope.events[i].status;
							  			}
							  		}
							  	}
							  	return '';
							  }
							  
					/*********************************** Calendar ******************************************/
							CalendarConfig.dateFormatter = 'moment'; // use
							var originali18n = angular.copy(CalendarConfig.i18nStrings);
							CalendarConfig.i18nStrings.weekNumber = 'Semaine {week}';

							$window.moment = $window.moment || moment;
							moment.locale('fr', {
								week : {
									dow : 1 }
								});
							moment.locale('fr'); // change the locale to
							// french

							$scope.calendarView = 'month';
							$scope.setView = function(view) {
								$scope.calendarView = view;
							}

							$scope.viewDate = moment().startOf('month')
							.toDate();
							$scope.cellIsOpen = false;
							$scope.eventClicked = function(events) {
								alert.show('Clicked', events);
							};
							$scope.cellModifier = function(cell) {
								console.log(cell);
								if (cell.label % 2 === 1 && cell.inMonth) {
									cell.cssClass = 'odd-cell';
								}
								cell.label = '-' + cell.label + '-';
							};
							
							$scope.eventsNamesList ={};
							$scope.events=[];
							$scope.initEventsChaine = function(name){
								var model = $parse(name);
							//	console.log(JSON.stringify($scope.eventsNamesList));
							}

					/*********************************** OF Grid ****************************************** */

							$scope.pagingOptions = {
								pageSizes : [ 5, 10 ],
								pageSize : 10,
								currentPage : 1
							};
							$scope.colDefs = [];
							$scope.$watch(
								'myDataOF',
								function() {
									$scope.colDefs = [
									{
										field : 'numero',
										displayName : 'OF',
										width:'10%'
									},

									{
										field : 'type',
										displayName : 'type',
										width:'7%'
									},
									{
										field : 'partieInterresAbreviation',
										displayName : 'Client',
										width:'10%'
									},
									{
										field : 'produitReference',
										displayName : ' Réf Produit',
										width:'10%'
									},
									{
										field : 'atelier',
										displayName : 'atelier',
										width:'10%'
									},
									{
										field : 'dateLivraison  | date : "dd/MM/yyyy"',
										displayName : 'DateLiv',
										width:'6%'
									},
									{
										field : 'traitement',
										displayName : 'Traitement',
										width:'10%'
									},
									{
										field : 'special',
										displayName : 'Spécial',
										width:'6%'
										
									},
									{
										field : 'quantite',
										displayName : 'Qte Totale',
										width:'7%'
									},
									{
										field : 'quantitePlanifie',
										displayName : 'Qte Affectée',
										width:'7%'
									},
									{
										field : 'cadence',
										displayName : 'Cadence',
										width:'8%'
									},
									{
										field : 'prixUnitaire',
										displayName : 'PU',
										width:'8%'
									},
									{
										field : 'numeroInterne',
										displayName : 'NUM Interene',
										width:'8%'
									},
									
									{
										field : '',
										cellTemplate : '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="affecterOF()">Affecter</button></div>' ,
										width:'10%'
									} ];
								});

							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};
							$scope.totalServerItems = 0;

							$scope.setPagingData = function(data, page,
									pageSize) {
								var pagedData = data.slice((page - 1)
										* pageSize, page * pageSize);
								$scope.myDataOF = pagedData;
								$scope.totalServerItems = data.length;
								if (!$scope.$$phase) {
									$scope.$apply();
								}
							};
							$scope.getPagedDataAsync = function(pageSize, page,
								searchText) {
								setTimeout(
									function() {
										var data;
										var ordreFabricationCourant = {
												"aPlanifier" : true,
												 siteId	: $scope.listeChaineAtelier.atelier
										}

										if (searchText) {
											var ft = searchText
											.toLowerCase();

											$http
											.post(
												baseUrlGpao	+ "/ordreFabrication/rechercheOrdreFabricationMulticriterePlanning",
												ordreFabricationCourant)
											.success(
												function(
													largeLoad) {
													data = largeLoad.ordreFabricationValues
													.filter(function(
														item) {
														return JSON
														.stringify(
															item)
														.toLowerCase()
														.indexOf(
															ft) != -1;
													});
													$scope
													.setPagingData(
														data,
														page,
														pageSize);
												});

										} else {
											$http
											.post(
												baseUrlGpao	+ "/ordreFabrication/rechercheOrdreFabricationMulticriterePlanning",
												ordreFabricationCourant)
											.success(
												function(
													largeLoad) {
													$scope
													.setPagingData(
														largeLoad.ordreFabricationValues,
														page,
														pageSize);
												});
										}
									}, 100);
							};
							$scope.getPagedDataAsync(
								$scope.pagingOptions.pageSize,
								$scope.pagingOptions.currentPage);

							$scope
							.$watch(
								'pagingOptions',
								function(newVal, oldVal) {
									if (newVal !== oldVal
										&& newVal.currentPage !== oldVal.currentPage) {
										$scope
									.getPagedDataAsync(
										$scope.pagingOptions.pageSize,
										$scope.pagingOptions.currentPage,
										$scope.filterOptions.filterText);
								}
							}, true);
							$scope.$watch('filterOptions', function(newVal,
								oldVal) {
								if (newVal !== oldVal) {
									$scope.getPagedDataAsync(
										$scope.pagingOptions.pageSize,
										$scope.pagingOptions.currentPage,
										$scope.filterOptions.filterText);
								}
							}, true);

							$scope.gridOptions = {
								data : 'myDataOF',
								columnDefs : 'colDefs',
								enablePaging : true,
								enableRowSelection : true,
								enableHighlighting : true,
								showFooter : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
								enableFilter: true,
								floatingFilter: true
								
							};
					/**************************************RechercheElementPlanning** par chaine ****************************/
							$scope.rechercheElementPlanning = function(idChaine , index ){
								var planning ={
									chaine : idChaine
								}
								console.log("idChaine" + idChaine);
								$log.debug("recherche en cours ..");
								//$scope.events[index] = [] ;
								$http.post(
									baseUrlGpao+'/planning/rechercheMulticritere', planning)

								.success(
									function(resultat) {
										$scope.myData = resultat.list;
										console.log("liste : "+JSON.stringify($scope.myData.length));
										$scope.events[index].splice(0) ;
										for (var int = 0; int < $scope.myData.length; int++) {	
											
											var  quantiteProduite = $scope.myData[int].quantite - $scope.myData[int].quantiteProduite ;
											$scope.events[index].push({
												title: $scope.myData[int].referenceOf +"/"+ $scope.myData[int].produitReference+"/"+quantiteProduite+"/"+$scope.myData[int].quantite,
												color : $scope.color,
												textColor: $scope.textColor,
												id : $scope.myData[int].id,
												chaine: $scope.myData[int].chaineDesignation,
												idChaine :$scope.myData[int].idChaine ,
												cadence: $scope.myData[int].cadence,	
												quantite: 	$scope.myData[int].quantite	,
												qteAncienne :  $scope.myData[int].qteAncienne ,
												type :  $scope.myData[int].type ,								
												idOF : $scope.myData[int].idOF , 
												referenceOf :$scope.myData[int].referenceOf,
												startsAt : moment($scope.myData[int].dateDebut),				 									
												endsAt : moment($scope.myData[int].dateFinReel),
												debut : $scope.myData[int].dateDebut ,
												fin :$scope.myData[int].dateFinReel ,
												partieInterresAbreviation :$scope.myData[int].partieInterresAbreviation ,
												produitDesignation: $scope.myData[int].produitDesignation,
												produitReference : $scope.myData[int].produitReference,
												dateFinReel : $scope.myData[int].dateFinReel ,
												editable: false, 
												deletable: false, 
												draggable: true,
												resizable: true , 
												cssClass: 'a-css-class-name',
											});
										}

									}
									);
							}
							
			  	 
				/***************************************Affecter*************************************************/
							$scope.ofCourantCalendar ={} ;
							$scope.ofCourant = {
								mode : "add" 
							};
							$scope.affecterOF=function(){
								var index = this.row.rowIndex;
								$scope.ofCourant = $scope.myDataOF[index];
								//$scope.ofCourantCalendar = $scope.ofCourant ;
								$scope.ofCourant.mode="add";
								$scope.ofCourant.idOF = $scope.myDataOF[index].id ; 
								$scope.ofCourant.produitDesignation = $scope.myDataOF[index].produitDesignation ; 
								$scope.ofCourant.qteAncienne =  $scope.myDataOF[index].qteAncienne ;
								$scope.ofCourant.type =  $scope.myDataOF[index].type ;
								$scope.ofCourant.referenceOf = $scope.myDataOF[index].numero;
								$scope.ofCourant.dateFinReel = $scope.myDataOF[index].dateFinReel ;
								$scope.ofCourant.decaler = 'false' ;
								console.log("ici 1");
								console.log("ofCourant"
									+ JSON.stringify($scope.ofCourant, null, " "));
							}
							$scope.affecterOFCalendar=function(calendarEvent){	
								$scope.ofCourant ={

								};
								$scope.ofCourant.cadence = calendarEvent.cadence;
								$scope.ofCourant.status = calendarEvent.title;
								$scope.ofCourant.numero =calendarEvent.of;
								$scope.ofCourant.referenceOf =calendarEvent.referenceOf ;
								$scope.ofCourant.idOF =calendarEvent.idOF ;								
								$scope.ofCourant.idChaine=calendarEvent.idChaine ;
								$scope.ofCourant.designation = calendarEvent.chaine ;
								$scope.ofCourant.id = calendarEvent.id;
								$scope.ofCourant.quantite = calendarEvent.quantite;
								$scope.ofCourant.QteTotal = calendarEvent.quantite;
								$scope.ofCourant.mode  = "edit" ; 
								$scope.ofCourant.dateDebut  = calendarEvent.debut ; 
								$scope.ofCourant.dateFinReel = calendarEvent.fin;
								$scope.ofCourant.produitDesignation = calendarEvent.produitDesignation ;
								$scope.ofCourant.produitReference = calendarEvent.produitReference ;
								$scope.ofCourant.partieInterresAbreviation = calendarEvent.partieInterresAbreviation ;
								$scope.ofCourant.type = calendarEvent.type ;
								$scope.ofCourant.qteAncienne = calendarEvent.qteAncienne ;
								$scope.ofCourant.decaler = 'false' ;
								$scope.ofCourant.datedeb = new Date(calendarEvent.debut); 
								var time = calendarEvent.dateFinReel;
							
								$scope.ofCourant.dateFinReel = new Date(time);
								console.log("ofCourant"
									+ JSON.stringify($scope.ofCourant,
										null, " "));
								
								console.log("ici 2 ");
							}
					/***************************************Création dans le planning*************************************************/
							$scope.change= function(){
								console.log("change chaine " + JSON.stringify($scope.ofCourant.idChaine,
										null, " "));
								$http
								.get(
									baseUrlGpao
									+ "/chaine/getId:"+$scope.ofCourant.idChaine)

								.success(
									function(resultat) {
										$scope.ofCourant.dateDebut = resultat.dateLibre ;
									});
							//$scope.ofCourant.dateDebut = $scope.ofCourant.idChaine.dateLibre ;	

							}
							$scope.enregistrerOFChaine = function(
								) {
								//console.log("iiiiiii"+JSON.stringify($scope.ofCourant));
								console.log("ofCourant"
									+ JSON.stringify($scope.ofCourant,
										null, " "));
								var events= {
												ofCourant : $scope.ofCourant ,
												id : $scope.id,
												idOF : $scope.idOF,
												idChaine : $scope.idChaine,
												quantite : $scope.quantite,
												quantiteProduite : $scope.quantitePlanifie,
												status : $scope.status,
												dateDebut : $scope.dateDebut,
												dateFinPrevue : $scope.dateFinPrevue,
												dateLivraison : $scope.dateLivraison 

								};

								console.log(JSON.stringify($scope.events));
								$scope.of ={

												//id : $scope.ofCourant.id,
												idOF : $scope.ofCourant.idOF,
												idSite : $scope.listeChaineAtelier.atelier,
												quantite : $scope.ofCourant.quantite,
												quantiteProduite : $scope.ofCourant.quantitePlanifie,
												status : $scope.ofCourant.status,
												dateDebut : $scope.ofCourant.dateDebut,
												dateFinPrevue :$scope.ofCourant.dateFinPrevue,
												cadence : $scope.ofCourant.cadence ,
												qteAncienne :$scope.ofCourant.qteAncienne ,
												type : $scope.ofCourant.type ,
												idChaine : $scope.ofCourant.idChaine ,
												decaler : $scope.ofCourant.decaler
								}; 
								// if($scope.ofCourant.mode == "add"){
								// 	$scope.of.idChaine = $scope.ofCourant.idChaine ;
								// }
								// if($scope.ofCourant.mode == "edit"){
								// 	$scope.of.idChaine = $scope.ofCourant.idChaine ;
								// }
								if ($scope.ofCourant.mode == "add") {
									$http
												.post(baseUrlGpao+"/planning/creer",$scope.of)
									console.log("objet de creation "+JSON.stringify($scope.of));

								//	$scope.rechercheElementPlanning($scope.of.idChaine.id);
												//console.log("list"+JSON.stringify);

												//console.log(JSON.stringify($scope.events));
								//	$scope.rechercheListPlanning($scope.of.idChaine);				
								} 
								else {
									 $scope.of.id = $scope.ofCourant.id ;
									console.log("ofCourant update "
									+ JSON.stringify($scope.ofCourant,
										null, " "));
									$http
												.put(baseUrlGpao+"/planning/modifier",$scope.of)
												//console.log("modifier"+JSON.stringify($scope.ofCourant));

												//$scope.rechercheElementPlanning();
												console.log("list"+JSON.stringify);
												$scope.events.push({


												title: $scope.ofCourant.numero,
												color : $scope.color,
												id:$scope.ofCourant.id,
												textColor: $scope.textColor,
												idChaine: $scope.ofCourant.chaine,
												idChaine : $scope.ofCourant.chaineDesignation ,
												cadence: $scope.ofCourant.cadence,													
												startsAt : moment($scope.ofCourant.dateDebut),													
												endsAt : moment($scope.ofCourant.dateFinPrevue),
												dateLiv : moment($scope.ofCourant.dateLivraison),
												editable: false, 
												deletable: false, 
												draggable: true,
												resizable: true , 
												cssClass: 'a-css-class-name',

									});
												console.log(JSON.stringify($scope.events));
								}
									$scope.rechercherOrdreFabrication() ;
									$scope.annulerAjout();
								
							}

					// * ********************** Rechercher Ordre FabricationOF ********************/
							// Rechercher Ordre Fabrication
							
							$scope.rechercherOrdreFabrication = function() {
								var ofParams = {
									"aPlanifier" : true,
									 siteId	: $scope.listeChaineAtelier.atelier
								};
								console.log("ofParams"
									+ JSON.stringify(ofParams));
								$http
								.post(
									baseUrlGpao
									+ "/ordreFabrication/rechercheOrdreFabricationMulticriterePlanning",
									ofParams)
								.success(
									function(resultat) {
										console.log("liste dordre "
									+ JSON.stringify(resultat));
										$scope.myDataOF = resultat.ordreFabricationValues;
										$scope
										.setPagingData(
										$scope.myDataOF,
										$scope.pagingOptions.currentPage,
										$scope.pagingOptions.pageSize);
										$scope.displayMode = "rechercher";
										$scope.displayAlert = "sleep";
									});
								$scope.getListeChaines();
							}

							/** *************** getListeChaineBy Atelier ****************** */
							$scope.getListeChaines = function() {
								console.log("ChaineByAtelier"+JSON.stringify($scope.listeChaineAtelier.atelier));
								$http
								.get(
									baseUrlGpao
									+ "/chaine/getChaineByAtelier:"+$scope.listeChaineAtelier.atelier)

								.success(
									function(resultat) {
										$scope.chainesList=resultat;
										var int = 0 ;
										angular.forEach($scope.chainesList , function(value, key){
											
											value.eventsName = "events-"+value.id;
											$scope.initEventsChaine(value.eventsName);
											console.log("rechercheElementPlanning with id " + $scope.chainesList[int].id );
											$scope.rechercheElementPlanning( $scope.chainesList[int].id , int) ;
											int = int + 1;
										});
									});
							}

					/******************************************************************************************/
							/* alert on eventClick */
							$scope.alertOnEventClick = function(event, jsEvent,
								view) {

								$scope.showCheckbox=true;
								$scope.mode = "edit";
								clickedEvent = event;
								console.log("event click"
									+ JSON.stringify(event, null, " "));

								$scope.eventCourant = {
												id : event.eventId,
												motif : event.motifId,
												status : event.statusId,
												description : event.description,
												client : event.clientId,
												dateDebut : event.start,
												dateFinPrevue : event.end,
												commande : event.commande,
												isEditable : event.isEditable
											}

								$('#datetimepickerDeb').data("DateTimePicker")
								.date(moment(new Date(event.start)));
								$('#datetimepickerFin').data("DateTimePicker")
								.date(moment(new Date(event.end)));

								var dateDebut = $('#datetimepickerDeb')
								.datetimepicker('date');
								var dateFinPrevue = $('#datetimepickerFin')
								.datetimepicker('date');								

								//Hide checkbox
								if(event.commande){
									$scope.showCheckbox=false;														
									
								}
							};

							/* alert on Drop */
							$scope.alertOnDrop = function(event, delta,
								revertFunc, jsEvent, ui, view) {
								$scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
							};

							/* alert on Resize */
							$scope.alertOnResize = function(event, delta,
								revertFunc, jsEvent, ui, view) {
								$scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
							};

							/* add and removes an event source of choice */
							$scope.addRemoveEventSource = function(sources,
								source) {
								var canAdd = 0;
								angular.forEach(sources, function(value, key) {
									if (sources[key] === source) {
										sources.splice(key, 1);
										canAdd = 1;
									}
								});
								if (canAdd === 0) {
									sources.push(source);
								}
							};

							/* remove event */
							$scope.remove = function(index) {
								$scope.events.splice(index, 1);
							};
							/* Change View */
							$scope.changeView = function(view, calendar) {
								CalendarConfig.calendars[calendar]
								.fullCalendar('changeView', view);
							};

							/* Change View */
							$scope.renderCalender = function(calendar) {
								if (CalendarConfig.calendars[calendar]) {
									CalendarConfig.calendars[calendar]
									.fullCalendar('render');
								}
							};
							/* Render Tooltip */
							$scope.eventRender = function(event, element, view) {
								element.attr({
									'tooltip' : event.title,
									'tooltip-append-to-body' : true
								});
								$compile(element)($scope);
							};

							/* config object */
							$scope.uiConfig = {
								calendar : {
									height : 450,
									editable : true,
									header : {
										left : 'month agendaWeek',
										center : 'title',
										right : 'today prev,next'
									},
									eventClick : $scope.alertOnEventClick,
									eventDrop : $scope.alertOnDrop,
									eventResize : $scope.alertOnResize,
									eventRender : $scope.eventRender
								}
							}

					/****************************************  delete Element planning ********************************/
					$scope.arreterEvent = function (){
						$scope.of ={

												id : $scope.ofCourant.id,
												idOF : $scope.ofCourant.idOF,
												idChaine :$scope.ofCourant.idChaine,
												quantite : $scope.ofCourant.quantite,
												quantiteProduite : $scope.ofCourant.quantitePlanifie,
												status : $scope.ofCourant.status,
												dateDebut : $scope.ofCourant.dateDebut,
												dateFinPrevue :$scope.ofCourant.dateFinPrevue,
												cadence : $scope.ofCourant.cadence ,
												decaler : $scope.ofCourant.decaler

								};

								$http
									.put(baseUrlGpao+"/planning/arreter",$scope.of) ;
					}
					$scope.deleteEvent = function (){
				
							$http.delete(baseUrlGpao+"/planning/supprimer",$scope.ofCourant) ;
					}



					/******************************************delete Event*********************************************/
						
					// Annulation de l'ajout
							$scope.annulerAjout = function() {
								
								/** CLose notif and init validation variables**/
								
												$scope.closeNotif();
											
												
												$scope.creation = true;
												//$scope.somme = 0;
												$scope.modeAdd = "Notok";
												$scope.resultatRechercheProduit = [];
												
												$scope.initNumeroErrorValue();
												
												
												
												$scope.id = {};

												
												$scope.ordreFabricationCourant= {};	
												$scope.ofCourant= {};														
												$scope.rechercheSEForm.$setPristine();
												$scope.ofCourant.mode == 'add' ;
												
												$scope.displayMode = "list";
							}


							$scope.initNumeroErrorValue = function(){
								$scope.numeroExistError = false;
							}

							// Mise à jour des ordres de Fabrications
							$scope.updateOrdreFabrication = function(ordreF) {
								
								ordreF.numOFBeforeUpdate = $scope.currentNumOF;

								console.log("OF" + JSON.stringify(ordreF,null," "));
								ordreF.partieInterresId = ordreF.clientId ;
								$http
								.post(
									baseUrlGpao
									+ "/ordreFabrication/modifierOrdreFabrication",
									ordreF)
								.success(
									function(modifiedId) {

										for (var i = 0; i < $scope.myDataOF.length; i++) {

											if ($scope.myDataOF[i].id == modifiedId) {
												$scope.myDataOF[i] = ordreF;
												break;
											}
										}


										$scope
										.setPagingData(
											$scope.myDataOF,
											$scope.pagingOptions.currentPage,
											$scope.pagingOptions.pageSize);


										$scope.annulerAjout();

									});
								
							}

							// Création d'un Ordre de Fabrication
							$scope.creerOrdreFabrication = function(ordreF) {

								ordreF.produitId = $scope.resultatRechercheProduit.produitId;
								ordreF.partieInterresId = $scope.resultatRechercheProduit.partieInterresId;
								

								ordreF.partieInterresId = ordreF.clientId ;
								$http
								.post(
									baseUrlGpao
									+ "/ordreFabrication/creerOrdreFabrication",
									ordreF)
								.success(
									function(result) {

										$http
										.get(
											baseUrlGpao
											+ "/ordreFabrication/getId:"
											+ result)
										.success(
											function(OF) {
												$scope.myDataOF.unshift(OF);
												$scope.annulerAjout();
												matrix = [];
											})


									});
								
							}
							$scope.supprimerOrdreFabrication = function() {
								
								$http(
								{
									method : "DELETE",
									url : baseUrlGpao
									+ "/ordreFabrication/supprimerOrdreFabrication:"
									+ $scope.myDataOF[$scope.index].id
								}).success(function() {
									$scope.myDataOF.splice($scope.index, 1);
									
								});
								
								$scope.rechercherOrdreFabrication({});
								$scope.closePopupDelete();
								
							};

					/*****************************************************************************************************/
							$scope.reset = function() {

								$scope.eventCourant = "";
								$('#datetimepickerDeb').datetimepicker({
									defaultDate : date
								});
								$('#datetimepickerFin').datetimepicker({
									defaultDate : date
								});

								$scope.form.$setPristine();
								$scope.mode = "add";
							}


					/***************************************** Init *****************************************************/

							$scope.init = function() {
							//	$scope.rechercherOrdreFabrication();
							//	$scope.getListeChaines();
							//	$scope.rechercheElementPlanning();
						}

						$scope.init();
					} ])
					/*********************************************Filtre***************************************************/
					.filter('showProduitFilterGpao', function() {
						return function(listeProduit, id) {
							var listeProduitFiltre = [];

							angular.forEach(listeProduit, function(produit, key){

								if(produit.id == id.id){

									listeProduitFiltre.push(produit);
								}

							});
							return listeProduitFiltre;
						};
					})

					.filter('filterSousFamilleGpao', function() {
						return function(listeSousFamille, id) {
							var listeSousFamilleFiltre = [];
										
										angular.forEach(listeSousFamille, function(sousFamille, key){
											
											if(sousFamille.id == id.id){
												
												listeSousFamilleFiltre.push(sousFamille);
											}

										});
										
										return listeSousFamilleFiltre;
									};
								
						})
						.config(function(ngQuickDateDefaultsProvider) {
						// Configure with icons from font-awesome
							return ngQuickDateDefaultsProvider.set({
							closeButtonHtml: "<i class='fa fa-times'></i>",
							buttonIconHtml: "<i class='glyphicon glyphicon-calendar'></i>",
							nextLinkHtml: "<i class='fa fa-chevron-right'></i>",
							prevLinkHtml: "<i class='fa fa-chevron-left'></i>",
							 // Take advantage of Sugar.js date parsing
    						parseDateFunction: function(str) {
     						 d = Date.create(str);
    						  return d.isValid() ? d : null;
   							 }
							});	
						});		
      
