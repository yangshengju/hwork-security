package com.hwork.junitexampletest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Created by yangshengju on 2019-7-24.
 */
public class DigitalAssetManagerTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void countsAssets() throws IOException{
//        File icon = temporaryFolder.newFile("icon.jpg");
        File assets = temporaryFolder.newFolder("assets");
        createAssets(assets, 3);
//        DigitalAssetManager dam = new DigitalAssetManager(icon,assets);
    }

    @Test
    public void throwsException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("happened");
        throw new NullPointerException("What happened?");
    }

    private void createAssets(File assets, int numberOfAssets) {
        for(int index=0;index<numberOfAssets;index++) {
            File asset =  new File(assets,String.format("assets-%d.mpg",index));
            try {
                Assert.assertTrue("Asset can't be created",asset.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
