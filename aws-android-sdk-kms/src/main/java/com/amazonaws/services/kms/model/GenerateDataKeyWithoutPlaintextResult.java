/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.kms.model;

import java.io.Serializable;

/**
 * 
 */
public class GenerateDataKeyWithoutPlaintextResult implements Serializable {

    /**
     * Ciphertext that contains the wrapped data key. You must store the blob
     * and encryption context so that the key can be used in a future decrypt
     * operation. <p>If you are using the CLI, the value is Base64 encoded.
     * Otherwise, it is not encoded.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 6144<br/>
     */
    private java.nio.ByteBuffer ciphertextBlob;

    /**
     * System generated unique identifier of the key to be used to decrypt
     * the encrypted copy of the data key.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     */
    private String keyId;

    /**
     * Ciphertext that contains the wrapped data key. You must store the blob
     * and encryption context so that the key can be used in a future decrypt
     * operation. <p>If you are using the CLI, the value is Base64 encoded.
     * Otherwise, it is not encoded.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 6144<br/>
     *
     * @return Ciphertext that contains the wrapped data key. You must store the blob
     *         and encryption context so that the key can be used in a future decrypt
     *         operation. <p>If you are using the CLI, the value is Base64 encoded.
     *         Otherwise, it is not encoded.
     */
    public java.nio.ByteBuffer getCiphertextBlob() {
        return ciphertextBlob;
    }
    
    /**
     * Ciphertext that contains the wrapped data key. You must store the blob
     * and encryption context so that the key can be used in a future decrypt
     * operation. <p>If you are using the CLI, the value is Base64 encoded.
     * Otherwise, it is not encoded.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 6144<br/>
     *
     * @param ciphertextBlob Ciphertext that contains the wrapped data key. You must store the blob
     *         and encryption context so that the key can be used in a future decrypt
     *         operation. <p>If you are using the CLI, the value is Base64 encoded.
     *         Otherwise, it is not encoded.
     */
    public void setCiphertextBlob(java.nio.ByteBuffer ciphertextBlob) {
        this.ciphertextBlob = ciphertextBlob;
    }
    
    /**
     * Ciphertext that contains the wrapped data key. You must store the blob
     * and encryption context so that the key can be used in a future decrypt
     * operation. <p>If you are using the CLI, the value is Base64 encoded.
     * Otherwise, it is not encoded.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 6144<br/>
     *
     * @param ciphertextBlob Ciphertext that contains the wrapped data key. You must store the blob
     *         and encryption context so that the key can be used in a future decrypt
     *         operation. <p>If you are using the CLI, the value is Base64 encoded.
     *         Otherwise, it is not encoded.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public GenerateDataKeyWithoutPlaintextResult withCiphertextBlob(java.nio.ByteBuffer ciphertextBlob) {
        this.ciphertextBlob = ciphertextBlob;
        return this;
    }

    /**
     * System generated unique identifier of the key to be used to decrypt
     * the encrypted copy of the data key.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     *
     * @return System generated unique identifier of the key to be used to decrypt
     *         the encrypted copy of the data key.
     */
    public String getKeyId() {
        return keyId;
    }
    
    /**
     * System generated unique identifier of the key to be used to decrypt
     * the encrypted copy of the data key.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     *
     * @param keyId System generated unique identifier of the key to be used to decrypt
     *         the encrypted copy of the data key.
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
    
    /**
     * System generated unique identifier of the key to be used to decrypt
     * the encrypted copy of the data key.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     *
     * @param keyId System generated unique identifier of the key to be used to decrypt
     *         the encrypted copy of the data key.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public GenerateDataKeyWithoutPlaintextResult withKeyId(String keyId) {
        this.keyId = keyId;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCiphertextBlob() != null) sb.append("CiphertextBlob: " + getCiphertextBlob() + ",");
        if (getKeyId() != null) sb.append("KeyId: " + getKeyId() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getCiphertextBlob() == null) ? 0 : getCiphertextBlob().hashCode()); 
        hashCode = prime * hashCode + ((getKeyId() == null) ? 0 : getKeyId().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof GenerateDataKeyWithoutPlaintextResult == false) return false;
        GenerateDataKeyWithoutPlaintextResult other = (GenerateDataKeyWithoutPlaintextResult)obj;
        
        if (other.getCiphertextBlob() == null ^ this.getCiphertextBlob() == null) return false;
        if (other.getCiphertextBlob() != null && other.getCiphertextBlob().equals(this.getCiphertextBlob()) == false) return false; 
        if (other.getKeyId() == null ^ this.getKeyId() == null) return false;
        if (other.getKeyId() != null && other.getKeyId().equals(this.getKeyId()) == false) return false; 
        return true;
    }
    
}
    