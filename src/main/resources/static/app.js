var app = angular.module('mcqApp', []);

app.controller('MCQController', function($scope, $http) {
    $scope.questionCount = 5;  // Default value
    $scope.questions = [];
    $scope.score = 0;
    $scope.resultVisible = false;
    $scope.currentPage = 1;
    $scope.totalPages = 1;
    $scope.totalQuestions = 0; // Will be fetched from backend

    $scope.updatePagination = function() {
        $scope.currentPage = 1; // Reset to the first page when question count changes
        $scope.fetchQuestions();
    };

    $scope.fetchQuestions = function() {
        $http.get('http://localhost:8080/questions', { params: { count: $scope.questionCount, page: $scope.currentPage } })
            .then(function(response) {
                $scope.questions = response.data.questions;
                $scope.totalQuestions = response.data.totalQuestions;
                $scope.totalPages = Math.ceil($scope.totalQuestions / $scope.questionCount);
                $scope.resultVisible = false;  // Hide the result if questions are reloaded
            }, function(error) {
                console.error('Error fetching questions:', error);
            });
    };

    $scope.nextPage = function() {
        if ($scope.currentPage < $scope.totalPages) {
            $scope.currentPage++;
            $scope.fetchQuestions();
        }
    };

    $scope.prevPage = function() {
        if ($scope.currentPage > 1) {
            $scope.currentPage--;
            $scope.fetchQuestions();
        }
    };

    $scope.submitAnswers = function() {
        var answers = $scope.questions.map(function(question) {
            return {
                id: question.id,
                selectedOption: question.selectedOption
            };
        });

        $http.post('http://localhost:8080/submit', answers)
            .then(function(response) {
                $scope.score = response.data;
                $scope.resultVisible = true;  // Show the result
            }, function(error) {
                console.error('Error submitting answers:', error);
            });
    };

    // Initialize pagination and fetch initial questions
    $scope.updatePagination();
});
