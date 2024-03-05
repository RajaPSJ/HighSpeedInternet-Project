var app = angular.module('customerOrderApp', []);

app.controller('CustomerOrderController', function($scope, $http) {
    $scope.customer = {
        name: '',
        phoneNumber: '',
        address: '',
        zipcode: ''
    };

    $scope.showEligibility = false;
    $scope.eligibility = '';

    $scope.checkEligibility = function() {
        // Here you would implement the logic to check eligibility based on customer details
        // For demonstration purposes, let's assume the customer is eligible if zip code starts with '9'

        $http.post('http://localhost:8080/customer-api/customer/check', $scope.customer)
            .then(function (response) {
                console.log(response.data)
                // Assuming the response.data is a base64-encoded string
                $scope.responseImage = 'data:image/png;base64,' + response.data;
                $scope.eligibility = 'Eligible for connection';
            }, function (error) {
                console.error('Error posting customer details:', error);
                $scope.responseImage = null;
                $scope.eligibility = 'Not eligible for connection';
            });
        // if ($scope.customer.zipcode.startsWith('9')) {
        //     $scope.eligibility = 'Eligible for connection';
        // } else {
        //     $scope.eligibility = 'Not eligible for connection';
        // }
        $scope.showEligibility = true;
    };

    // $scope.postCustomerDetails = function () {
    //     $http.post('http://localhost:8080/customer-api/customer/check', $scope.customer)
    //         .then(function (response) {
    //             console.log(response.data)
    //             // Assuming the response.data is a base64-encoded string
    //             $scope.responseImage = response.data;
    //         }, function (error) {
    //             console.error('Error posting customer details:', error);
    //         });
    // };
});