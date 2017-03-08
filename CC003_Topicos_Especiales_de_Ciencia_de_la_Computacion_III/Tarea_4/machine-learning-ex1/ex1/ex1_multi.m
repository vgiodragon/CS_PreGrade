%% Machine Learning Online Class
%  Exercise 1: Linear regression with multiple variables
%
%  Instructions
%  ------------
% 
%  This file contains code that helps you get started on the
%  linear regression exercise. 
%
%  You will need to complete the following functions in this 
%  exericse:
%
%     warmUpExercise.m
%     plotData.m
%     gradientDescent.m
%     computeCost.m
%     gradientDescentMulti.m
%     computeCostMulti.m
%     featureNormalize.m
%     normalEqn.m
%
%  For this part of the exercise, you will need to change some
%  parts of the code below for various experiments (e.g., changing
%  learning rates).
%

%% Initialization

%% ================ Part 1: Feature Normalization ================

%% Clear and Close Figures
clear ; close all; clc

fprintf('Loading data ...\n');

%% Load Data
data = load('ex1data2.txt');
X = data(:, 1:2);
y = data(:, 3);
m = length(y);

% Print out some data points
fprintf('First 10 examples from the dataset: \n');
fprintf(' x = [%.0f %.0f], y = %.0f \n', [X(1:10,:) y(1:10,:)]');

fprintf('Program paused. Press enter to continue.\n');
pause;

% Scale features and set them to zero mean
fprintf('Normalizing Features ...\n');

[X mu sigma] = featureNormalize(X);

% Add intercept term to X
X = [ones(m, 1) X];


%% ================ Part 2: Gradient Descent ================

% ====================== YOUR CODE HERE ======================
% Instructions: We have provided you with the following starter
%               code that runs gradient descent with a particular
%               learning rate (alpha). 
%
%               Your task is to first make sure that your functions - 
%               computeCost and gradientDescent already work with 
%               this starter code and support multiple variables.
%
%               After that, try running gradient descent with 
%               different values of alpha and see which one gives
%               you the best result.
%
%               Finally, you should complete the code at the end
%               to predict the price of a 1650 sq-ft, 3 br house.
%
% Hint: By using the 'hold on' command, you can plot multiple
%       graphs on the same figure.
%
% Hint: At prediction, make sure you do the same feature normalization.
%

fprintf('Running gradient descent ...\n');


num_iters = 400;

% Init Theta and Run Gradient Descent 
theta1 = zeros(3, 1);
theta2 = zeros(3, 1);
theta3 = zeros(3, 1);
theta4 = zeros(3, 1);
theta5 = zeros(3, 1);
theta6 = zeros(3, 1);

% Choose some alpha value
alpha1=0.3;
alpha2=0.1;
alpha3=0.03;
alpha4=0.01;
alpha5=0.003;
alpha6=0.001;

% computing the values

[theta1, J_history1] = gradientDescentMulti(X, y, theta1, 0.3, num_iters);

[theta2, J_history2] = gradientDescentMulti(X, y, theta2, 0.1, num_iters);

[theta3, J_history3] = gradientDescentMulti(X, y, theta3, 0.03, num_iters);

[theta4, J_history4] = gradientDescentMulti(X, y, theta4, 0.01, num_iters);

[theta5, J_history5] = gradientDescentMulti(X, y, theta5, 0.003, num_iters);

[theta6, J_history6] = gradientDescentMulti(X, y, theta6, 0.001, num_iters);

% Plot the convergence graph
figure;
plot(1:numel(J_history1),J_history1,'-b','LineWidth', 2);
title("Alpha = 0.3");
xlabel('Number of iterations');
ylabel('Cost J');
%hold on;
figure;
plot(1:numel(J_history2),J_history2,'-r','LineWidth', 2);
title("Alpha = 0.1");
xlabel('Number of iterations');
ylabel('Cost J');

figure;
plot(1:numel(J_history3),J_history3,'-m','LineWidth', 2);
title("Alpha = 0.03");
xlabel('Number of iterations');
ylabel('Cost J');

figure;
plot(1:numel(J_history4),J_history4,'-k','LineWidth', 2);
title("Alpha = 0.01");
xlabel('Number of iterations');
ylabel('Cost J');

figure;
plot(1:numel(J_history5),J_history5,'-y','LineWidth', 2);
title("Alpha = 0.003");
xlabel('Number of iterations');
ylabel('Cost J');

figure;
plot(1:numel(J_history6),J_history6,'-g','LineWidth', 2);
title("Alpha = 0.001");
xlabel('Number of iterations');
ylabel('Cost J');

figure;
plot(1:50,J_history1(1:50),1:50,J_history2(1:50),1:50,J_history3(1:50),1:50,J_history4(1:50),1:50,J_history5(1:50),1:50,J_history6(1:50));
legend('Alpha=0.3','Alpha=0.1','Alpha=0.03','Alpha=0.01','Alpha=0.003','Alpha=0.001');


% Display gradient descent's result
fprintf("\nTheta computed from gradient descent with alpha: %0.4f\n",alpha1);
fprintf(" %f \n", theta1);
fprintf("\n\n");

fprintf("\nTheta computed from gradient descent with alpha: %0.4f\n",alpha2);
fprintf(" %f \n", theta2);
fprintf("\n\n");

fprintf("\nTheta computed from gradient descent with alpha: %0.4f\n",alpha3);
fprintf(" %f \n", theta3);
fprintf("\n\n");

fprintf("\nTheta computed from gradient descent with alpha: %0.4f\n",alpha4);
fprintf(" %f \n", theta4);
fprintf("\n\n");

fprintf("\nTheta computed from gradient descent with alpha: %0.4f\n",alpha5);
fprintf(" %f \n", theta5);
fprintf("\n\n");

fprintf("\nTheta computed from gradient descent with alpha: %0.4f\n",alpha6);
fprintf(" %f \n", theta6);
fprintf("\n\n");

% Estimate the price of a 1650 sq-ft, 3 br house
% ====================== YOUR CODE HERE ======================
% Recall that the first column of X is all-ones. Thus, it does
% not need to be normalized.
d = [1650 3];
d = (d - mu) ./ sigma;
d = [ones(1, 1) d];
price1 = d * theta1; % You should change this
price2 = d * theta2; % You should change this
price3 = d * theta3; % You should change this
price4 = d * theta4; % You should change this
price5 = d * theta5; % You should change this
price6 = d * theta6; % You should change this

% ============================================================

fprintf(["\n\nPredicted price of a 1650 sq-ft, 3 br house (using gradient descent):\n $%f, with alpha: %0.4f\n"], price1,alpha1);
         
fprintf(["\n\nPredicted price of a 1650 sq-ft, 3 br house (using gradient descent):\n $%f, with alpha: %0.4f\n"], price2,alpha2);

fprintf(["\n\nPredicted price of a 1650 sq-ft, 3 br house (using gradient descent):\n $%f, with alpha: %0.4f\n"], price3,alpha3);

fprintf(["\n\nPredicted price of a 1650 sq-ft, 3 br house (using gradient descent):\n $%f, with alpha: %0.4f\n"], price4,alpha4);
         
fprintf(["\n\nPredicted price of a 1650 sq-ft, 3 br house (using gradient descent):\n $%f, with alpha: %0.4f\n"], price5,alpha5);

fprintf(["\n\nPredicted price of a 1650 sq-ft, 3 br house (using gradient descent):\n $%f, with alpha: %0.4f\n"], price6,alpha6);

fprintf('Program paused. Press enter to continue.\n');
pause;

%% ================ Part 3: Normal Equations ================

fprintf('\n\nSolving with normal equations...\n');

% ====================== YOUR CODE HERE ======================
% Instructions: The following code computes the closed form 
%               solution for linear regression using the normal
%               equations. You should complete the code in 
%               normalEqn.m
%
%               After doing so, you should complete this code 
%               to predict the price of a 1650 sq-ft, 3 br house.
%

%% Load Data
data = csvread('ex1data2.txt');
X = data(:, 1:2);
y = data(:, 3);
m = length(y);

% Add intercept term to X
X = [ones(m, 1) X];

% Calculate the parameters from the normal equation
theta1 = normalEqn(X, y);

% Display normal equation's result
fprintf('\n\nTheta computed from the normal equations: \n');
fprintf(' %f \n', theta1);
fprintf('\n');


% Estimate the price of a 1650 sq-ft, 3 br house
% ====================== YOUR CODE HERE ======================
d = [1 1650 3];
price1 = d * theta1; % You should change this


% ============================================================

fprintf(['\nPredicted price of a 1650 sq-ft, 3 br house ' ...
         '(using normal equations):\n $%f\n\n'], price1);

