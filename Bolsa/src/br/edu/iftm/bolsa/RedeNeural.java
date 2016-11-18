/*
 * Encog(tm) Java Examples v3.3
 * http://www.heatonresearch.com/encog/
 * https://github.com/encog/encog-java-examples
 *
 * Copyright 2008-2014 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package br.edu.iftm.bolsa;

import java.util.ArrayList;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

/**
 * XOR: This example is essentially the "Hello World" of neural network
 * programming.  This example shows how to construct an Encog neural
 * network to predict the output from the XOR operator.  This example
 * uses backpropagation to train the neural network.
 * 
 * This example attempts to use a minimum of Encog features to create and
 * train the neural network.  This allows you to see exactly what is going
 * on.  For a more advanced example, that uses Encog factories, refer to
 * the XORFactory example.
 * 
 */
public class RedeNeural {


	public static void training(ArrayList<Double> closes) {
		//dividindo em 80%
		int tam80 = (int) (closes.size()*0.8);
		int tam20 = (int) (closes.size()*0.2);
		//dividindo em 20%

		int num_linhas = closes.size() -5;
		int num_linhas80 = tam80 -5;
		int num_linhas20 = tam20 -5;
		double XOR_INPUT80[][] = new double[num_linhas80][5];
		double XOR_INPUT20[][] = new double[num_linhas20][5];
		double XOR_IDEAL80[][] = new double[num_linhas80][1];
		double XOR_IDEAL20[][] = new double[num_linhas20][1];
		int iIdeal = 0;

		ArrayList<Double> lista = new ArrayList();

		for (int i = 0; i < num_linhas; i++) {
			for (int j = 0; j < 5; j++){
				//anterior-atual/anterior
				//atual - anterior/atual - edson
				if(i<num_linhas80){
					if(i == 0 && j == 0){
						XOR_INPUT80[i][j] = 0;
						iIdeal = i+j;
					}else{
						XOR_INPUT80[i][j] = (closes.get(i+j) - closes.get((i+j)-1)) / closes.get((i+j));
						iIdeal = i+j;
					}
				}else{
					XOR_INPUT20[i][j] = (closes.get(i+j) - closes.get((i+j)-1)) / closes.get((i+j));
					iIdeal = i+j;
				}
			}
			iIdeal++;
			if(i<num_linhas80){
				XOR_IDEAL80[i][0] = (closes.get(iIdeal) - closes.get(iIdeal-1)) / closes.get(iIdeal);
			}else{
				XOR_IDEAL20[i][0] = (closes.get(iIdeal) - closes.get(iIdeal-1)) / closes.get(iIdeal);
			}
		}
		/*
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < num_linhas; j++) {
				System.out.println(XOR_INPUT80[j][i]);
			}	
		}

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < num_linhas; j++) {
				System.out.println(XOR_IDEAL80[j][i]);
			}	
		}
*/
		//System.exit(0);
		/*
		 * The main method.
		 * @param args No arguments are used.
		 */
		// create a neural network, without using a factory
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(5));
		network.addLayer(new BasicLayer(new ActivationTANH(),true,5));//original
		network.addLayer(new BasicLayer(new ActivationTANH(),false,1));//original
		network.getStructure().finalizeStructure();
		network.reset();

		// create training data
		MLDataSet trainingSet80 = new BasicMLDataSet(XOR_INPUT80, XOR_IDEAL80);

		MLDataSet trainingSet20 = new BasicMLDataSet(XOR_INPUT20, XOR_IDEAL20);

		// train the neural network
		final ResilientPropagation train = new ResilientPropagation(network, trainingSet80);

		int epoch = 1;

		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while(train.getError() > 0.001);
		train.finishTraining();

		// test the neural network
		System.out.println("Neural Network Results:");
		for(MLDataPair pair: trainingSet20 ) {
			final MLData output = network.compute(pair.getInput());
			System.out.println(pair.getInput().getData(0) + "," + pair.getInput().getData(1)
					+ ", actual=" + output.getData(0) + ",ideal=" + pair.getIdeal().getData(0));

		}


		Encog.getInstance().shutdown();
	}
}
