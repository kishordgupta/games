
package com.rhymes.ge.pw.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/** Describes a set of assets. */
public class AssetPack {
	private final Array<AssetDescriptor> descriptors = new Array();

	public Array<AssetDescriptor> getDescriptors () {
		return descriptors;
	}

	public void add (AssetPack assetPackage) {
		descriptors.addAll(assetPackage.descriptors);
	}

	public void add (String fileName, Class assetType) {
		add(fileName, assetType, null);
	}

	public <T> void add (String fileName, Class<T> assetType, AssetLoaderParameters<T> params) {
		descriptors.add(new AssetDescriptor(fileName, assetType, params));
	}
	
	public void addTexture(String fileName) {
		add(fileName, Texture.class);
	}

	public String toString () {
		StringBuffer buffer = new StringBuffer();
		for (AssetDescriptor desc : descriptors) {
			buffer.append(desc);
			buffer.append('\n');
		}
		return buffer.toString();
	}
}
