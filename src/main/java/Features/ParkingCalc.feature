@Parking
Feature: Calculate different parking fees
Scenario Outline: Calculate parkinf fee for "<TestCase>"
	 Given The user Opened the AUT.
	 Then the user waits for the page to load.
	 And the user puts the data for "<TestCase>".
	 Then the user waits for the page to load.
	 Then submits the input data.
	 Then the user waits for the page to load.
	 Then validate the outcome for "<TestCase>".
	 Examples:
	 	| TestCase |
	 	| TC001	   |
#	 	| TC002	   |
#	 	| TC003	   |
#	 	| TC004	   |
#	 	| TC005	   |
#	 	| TC006	   |
#	 	| TC007	   |
#	 	| TC008	   |
#	 	| TC009	   |
#	 	| TC010	   |
#	 	| TC011	   |
#	 	| TC012	   |
#	 	| TC013	   |
#	 	| TC014	   |
#	 	| TC015	   |
#	 	| TC016	   |
#	 	| TC017	   |
#	 	| TC018	   |
#	 	| TC019	   |
#	 	| TC020	   |
#	 	| TC021	   |
#	 	| TC022	   |
#	 	| TC023	   |
#	 	| TC024	   |
#	 	| TC025	   |
#	 	| TC026	   |
#	 	| TC027	   |
#	 	| TC028	   |
#	 	| TC029	   |
#	 	| TC030	   |
#	 	| TC031	   |
#	 	| TC032	   |
#	 	| TC033	   |
#	 	| TC034	   |
#	 	| TC035	   |
#	 	| TC036	   |
#	 	| TC037	   |
#	 	| TC038	   |
#	 	| TC039	   |
#	 	| TC040	   |
#	 	| TC041	   |
#	 	| TC042	   |
#	 	| TC043	   |
#	 	| TC044	   |
#	 	| TC045	   |
#	 	| TC046	   |
#	 	| TC047	   |
#	 	| TC048	   |
#	 	| TC049	   |
#	 	| TC050	   |
#	 	| TC051	   |
#	 	| TC052	   |
#	 	| TC053	   |
#	 	| TC054	   |